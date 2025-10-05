#!/bin/bash
# Enhanced GitHub Issue Creation Script
set -euo pipefail

# === CONFIGURATION ===
ASSIGNEE="${1:-$(gh api user --jq '.login')}"
LABEL_FILTER="${2:-}"
ISSUES_DIR=".github/issues"

# === Theme from release-pr.sh ===
readonly GREEN='\033[1;32m'
readonly ORANGE='\033[38;5;214m'
readonly RED='\033[1;31m'
readonly WHITE='\033[1;37m'
readonly BLUE='\033[1;34m'
readonly PURPLE='\033[1;35m'
readonly CYAN='\033[1;36m'
readonly YELLOW='\033[1;33m'
readonly MAGENTA='\033[1;35m'
readonly NC='\033[0m'

# === Icons from release-pr.sh ===
readonly ICON_PASS="${GREEN}✓${NC}"
readonly ICON_WARN="${ORANGE}⚠${NC}"
readonly ICON_FAIL="${RED}✗${NC}"
readonly ICON_INFO="${BLUE}ℹ${NC}"
readonly ICON_SKIP="${WHITE}○${NC}"
readonly ICON_ADD="${PURPLE}+${NC}"
readonly ICON_UPDATE="${CYAN}↻${NC}"

# === Author Information ===
readonly AUTHOR_NAME="ANUJ KUMAR"
readonly AUTHOR_EMAIL="anujpatiyal@live.in"
readonly AUTHOR_LINKEDIN="https://www.linkedin.com/in/anuj-kumar-qa/"
readonly AUTHOR_DESIGNATION="QA Consultant & Test Automation Architect"

# === Global Variables ===
declare -A known_labels=()
declare -a existing_labels_lower=()
declare -i created=0 skipped=0 failed=0 total=0

# === FUNCTIONS ===

log_info() {
    echo -e "${ICON_INFO} ${BLUE}$1${NC}" >&2
}

log_warn() {
    echo -e "${ICON_WARN} ${ORANGE}$1${NC}" >&2
}

log_success() {
    echo -e "${ICON_PASS} ${GREEN}$1${NC}" >&2
}

log_error() {
    echo -e "${ICON_FAIL} ${RED}$1${NC}" >&2
    exit 1
}

log_skip() {
    echo -e "${ICON_SKIP} ${WHITE}$1${NC}" >&2
}

normalize_title() {
    echo "$1" | perl -CSDA -pe 's/\p{So}//g' | sed 's/[^[:alnum:][:space:]]//g' | tr '[:upper:]' '[:lower:]' | xargs
}

cache_existing_labels() {
    log_info "Caching existing labels..."
    local existing_labels
    if ! mapfile -t existing_labels < <(gh label list --json name --jq '.[].name' 2>/dev/null); then
        log_warn "Failed to fetch existing labels, continuing with empty list"
        existing_labels=()
    fi
    existing_labels_lower=()
    for label in "${existing_labels[@]}"; do
        existing_labels_lower+=("$(echo "$label" | tr '[:upper:]' '[:lower:]')")
    done
    log_success "Cached ${#existing_labels_lower[@]} existing labels"
}

create_label_if_needed() {
    local label_name="$1"
    label_name=$(echo "$label_name" | sed 's/^[[:space:]]*//;s/[[:space:]]*$//' | tr -d '"')

    if [[ -z "$label_name" ]]; then
        return 0
    fi

    if [[ -n "${known_labels[$label_name]:-}" ]]; then
        return 0
    fi

    local lower_label=$(echo "$label_name" | tr '[:upper:]' '[:lower:]')

    # Check if label exists (case insensitive)
    local label_exists=0
    for existing_label in "${existing_labels_lower[@]}"; do
        if [[ "$existing_label" == "$lower_label" ]]; then
            label_exists=1
            break
        fi
    done

    if [[ $label_exists -eq 1 ]]; then
        known_labels[$label_name]=1
        log_skip "Label already exists: $label_name"
        return 0
    fi

    # Create label with random color
    local label_color=$(openssl rand -hex 3 2>/dev/null || echo "0366d6")
    log_info "Creating label: $label_name"

    if gh label create "$label_name" --color "$label_color" --description "Automatically created" &>/dev/null; then
        known_labels[$label_name]=1
        existing_labels_lower+=("$lower_label")
        log_success "Created label: $label_name"
        return 0
    else
        log_warn "Failed to create label: $label_name (may already exist)"
        return 1
    fi
}

check_issue_exists() {
    local clean_title="$1"
    local compare_title=$(normalize_title "$clean_title")

    # Get ALL issues (both open and closed) with their titles
    local existing_issues
    if ! mapfile -t existing_issues < <(gh issue list --limit 200 --state all --json title,state,number --jq '.[] | "\(.title)|\(.state)|\(.number)"' 2>/dev/null); then
        log_warn "Failed to fetch existing issues, assuming none exist"
        return 1
    fi

    # Check if any existing title matches when normalized (regardless of state)
    for issue in "${existing_issues[@]}"; do
        local existing_title=$(echo "$issue" | cut -d'|' -f1)
        local state=$(echo "$issue" | cut -d'|' -f2)
        local issue_number=$(echo "$issue" | cut -d'|' -f3)

        local normalized_existing=$(normalize_title "$existing_title")
        local normalized_new=$(normalize_title "$clean_title")

        if [[ "$normalized_existing" == "$normalized_new" ]]; then
            log_skip "Issue already exists (#$issue_number - $state): $clean_title"
            return 0  # Issue exists (regardless of state)
        fi
    done

    return 1  # Issue doesn't exist
}

validate_issue_file() {
    local file="$1"

    if [[ ! -f "$file" ]]; then
        log_warn "File not found: $file"
        return 1
    fi

    if [[ ! -s "$file" ]]; then
        log_warn "Empty issue file: $file"
        return 1
    fi

    # Check if file has required title
    if ! grep -q "^title:" "$file"; then
        log_warn "Missing title in issue file: $file"
        return 1
    fi

    return 0
}

parse_issue_metadata() {
    local file="$1"
    local -n title_ref="$2"
    local -n labels_ref="$3"
    local -n milestone_ref="$4"
    local -n body_ref="$5"

    # Parse title
    title_ref=$(awk -F': ' '/^title:/ {print $2; exit}' "$file" | sed 's/^"//;s/"$//' | xargs)

    # Parse labels
    local labels_raw=$(awk -F': ' '/^labels:/ {print $2; exit}' "$file" | tr -d '[]"' | tr ',' '\n' | xargs -n1)
    labels_ref=()
    while IFS= read -r label; do
        if [[ -n "$label" ]]; then
            labels_ref+=("$label")
        fi
    done <<< "$labels_raw"

    # Parse milestone
    milestone_ref=$(awk -F': ' '/^milestone:/ {print $2; exit}' "$file" | tr -d '"' | xargs)

    # Parse body (content after second --- separator)
    body_ref=$(awk '/^---$/ {count++; next} count >= 2 {print}' "$file")

    # Add author information to body
    if [[ -n "$body_ref" ]]; then
        body_ref="${body_ref}

## 👤 Author
**${AUTHOR_NAME}** 🏅 ${AUTHOR_DESIGNATION}
- 📧 Email: [${AUTHOR_EMAIL}](mailto:${AUTHOR_EMAIL})
- 🔗 [LinkedIn Profile](${AUTHOR_LINKEDIN})"
    else
        body_ref="## 👤 Author
**${AUTHOR_NAME}** 🏅 ${AUTHOR_DESIGNATION}
- 📧 Email: [${AUTHOR_EMAIL}](mailto:${AUTHOR_EMAIL})
- 🔗 [LinkedIn Profile](${AUTHOR_LINKEDIN})"
    fi
}

create_issue_from_file() {
    local file="$1"

    # Validate file
    if ! validate_issue_file "$file"; then
        ((failed++))
        return 2
    fi

    # Parse metadata
    local clean_title
    local labels_array
    local milestone
    local body

    parse_issue_metadata "$file" clean_title labels_array milestone body

    if [[ -z "$clean_title" ]]; then
        log_warn "Empty title in file: $(basename "$file")"
        ((failed++))
        return 2
    fi

    # Check if issue already exists (including closed ones)
    if check_issue_exists "$clean_title"; then
        ((skipped++))
        return 1
    fi

    # Check label filter
    if [[ -n "$LABEL_FILTER" ]]; then
        local found_match=0
        for label in "${labels_array[@]}"; do
            if [[ "${label,,}" == "${LABEL_FILTER,,}" ]]; then
                found_match=1
                break
            fi
        done

        if [[ $found_match -eq 0 ]]; then
            log_skip "Skipping due to label filter: $clean_title"
            ((skipped++))
            return 1
        fi
    fi

    # Prepare milestone argument
    local milestone_arg=()
    if [[ -n "$milestone" ]]; then
        milestone_arg=("--milestone" "$milestone")
    fi

    # Create labels and prepare label arguments
    local label_args=()
    for label in "${labels_array[@]}"; do
        if create_label_if_needed "$label"; then
            label_args+=("-l" "$label")
        fi
    done

    # Create the issue
    log_info "Creating issue: $clean_title"
    local issue_url
    if issue_url=$(gh issue create \
        --title "$clean_title" \
        --body "$body" \
        --assignee "$ASSIGNEE" \
        "${label_args[@]}" \
        "${milestone_arg[@]}" 2>&1); then

        log_success "Created issue: $issue_url"
        ((created++))
        return 0
    else
        # Check if the error is due to issue already existing (race condition)
        if echo "$issue_url" | grep -qi "already exists\|duplicate"; then
            log_skip "Issue already exists (race condition): $clean_title"
            ((skipped++))
            return 1
        else
            log_warn "Failed to create issue: $clean_title"
            echo "Error: $issue_url" >&2
            ((failed++))
            return 2
        fi
    fi
}

print_summary() {
    echo -e "\n${PURPLE}╔══════════════════════════════════════════════════════════════╗"
    echo -e "║${NC}${WHITE}                📊  S U M M A R Y                       ${NC}${PURPLE}║"
    echo -e "╚══════════════════════════════════════════════════════════════╝${NC}"

    echo -e "\n${WHITE}Processed ${total} issue files:${NC}"
    echo -e "  ${ICON_PASS} ${GREEN}Created:    ${GREEN}${created}${NC}"
    echo -e "  ${ICON_SKIP} ${WHITE}Skipped:    ${WHITE}${skipped}${NC}"
    echo -e "  ${ICON_FAIL} ${RED}Failed:     ${RED}${failed}${NC}"

    # Calculate percentages
    if [[ $total -gt 0 ]]; then
        local created_pct=$((created * 100 / total))
        local skipped_pct=$((skipped * 100 / total))
        local failed_pct=$((failed * 100 / total))

        echo -e "\n${WHITE}Breakdown:${NC}"
        echo -e "  ${GREEN}✅ Success: ${created_pct}%${NC}"
        echo -e "  ${WHITE}⏭️  Skipped: ${skipped_pct}%${NC}"
        echo -e "  ${RED}❌ Failed:  ${failed_pct}%${NC}"
    fi

    # Final status message
    if [[ $failed -eq 0 ]]; then
        if [[ $created -gt 0 ]]; then
            echo -e "\n${GREEN}🎉 Successfully processed all issues!${NC}"
        else
            echo -e "\n${WHITE}ℹ️  No new issues created (all were skipped)${NC}"
        fi
    else
        echo -e "\n${RED}⚠️  Some issues failed to create${NC}"
    fi
}

print_header() {
    echo -e "\n${CYAN}╔══════════════════════════════════════════════════════════════╗"
    echo -e "║${NC}${WHITE}           🚀  G I T H U B  I S S U E S                ${NC}${CYAN}║"
    echo -e "║${NC}${BLUE}           Automated Issue Creation Script              ${NC}${CYAN}║"
    echo -e "╚══════════════════════════════════════════════════════════════╝${NC}"

    echo -e "\n${YELLOW}⚙️  Configuration:${NC}"
    echo -e "  ${MAGENTA}📁${NC} ${CYAN}Issues directory:${NC} ${WHITE}$ISSUES_DIR${NC}"
    echo -e "  ${MAGENTA}👤${NC} ${GREEN}Assignee:${NC} ${WHITE}$ASSIGNEE${NC}"
    if [[ -n "$LABEL_FILTER" ]]; then
        echo -e "  ${MAGENTA}🏷️${NC} ${ORANGE}Label filter:${NC} ${WHITE}$LABEL_FILTER${NC}"
    fi
    echo -e "  ${MAGENTA}✍️${NC} ${PURPLE}Author:${NC} ${WHITE}$AUTHOR_NAME${NC}"
    echo -e "  ${MAGENTA}💼${NC} ${BLUE}Designation:${NC} ${WHITE}$AUTHOR_DESIGNATION${NC}"
}

print_footer() {
    echo -e "\n${BLUE}╔══════════════════════════════════════════════════════════════╗"
    echo -e "║${NC}${WHITE}                   S C R I P T   B Y                    ${NC}${BLUE}║"
    echo -e "║${NC}${GREEN}          $AUTHOR_NAME                          ${NC}${BLUE}║"
    echo -e "║${NC}${CYAN}        $AUTHOR_DESIGNATION         ${NC}${BLUE}║"
    echo -e "║${NC}${WHITE}   📧 $AUTHOR_EMAIL        ${NC}${BLUE}║"
    echo -e "║${NC}${BLUE}   🔗 $AUTHOR_LINKEDIN ${NC}${BLUE}║"
    echo -e "╚══════════════════════════════════════════════════════════════╝${NC}"
}

main() {
    local start_time=$(date +%s)

    print_header

    if [[ ! -d "$ISSUES_DIR" ]]; then
        log_error "Directory '$ISSUES_DIR' not found."
    fi

    # Check if GitHub CLI is authenticated
    if ! gh auth status &>/dev/null; then
        log_error "GitHub CLI not authenticated. Please run 'gh auth login' first."
    fi

    cache_existing_labels

    local file_list=("$ISSUES_DIR"/*.md)
    total=${#file_list[@]}

    if [[ $total -eq 0 ]]; then
        log_warn "No .md files found in '$ISSUES_DIR'"
        exit 0
    fi

    echo -e "\n${CYAN}📝 Processing $total issue files...${NC}"

    # Reset counters
    created=0
    skipped=0
    failed=0

    for file in "${file_list[@]}"; do
        [[ -f "$file" ]] || continue
        echo -e "\n${YELLOW}📄 Processing:${NC} ${WHITE}$(basename "$file")${NC}"
        if ! create_issue_from_file "$file"; then
            # Error already logged, continue with next file
            continue
        fi
    done

    local end_time=$(date +%s)
    local duration=$((end_time - start_time))

    print_summary
    print_footer

    echo -e "\n${BLUE}⏱️  Execution time: ${duration} seconds${NC}"
}

# Handle script interruption
trap 'echo -e "\n${RED}Script interrupted by user${NC}"; exit 1' INT TERM

main "$@"
