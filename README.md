# 🚀 Hybrid Framework
**Advanced Driver Management - (v0.5.0)**
> Enterprise-grade test automation framework with advanced driver management, multi-browser support, and automatic driver handling

---

## 📛 Category Badges
### Version & Activity
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Anuj-Patiyal/hybrid-framework)
![GitHub release date](https://img.shields.io/github/release-date/Anuj-Patiyal/hybrid-framework)
![GitHub last commit](https://img.shields.io/github/last-commit/Anuj-Patiyal/hybrid-framework)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/Anuj-Patiyal/hybrid-framework)

### CI/CD & Quality
![CI Status](https://img.shields.io/github/actions/workflow/status/Anuj-Patiyal/hybrid-framework/main-ci.yml)
![POM Validation](https://img.shields.io/github/actions/workflow/status/Anuj-Patiyal/hybrid-framework/pom-validation.yml)
![Open Issues](https://img.shields.io/github/issues/Anuj-Patiyal/hybrid-framework)
![Closed Issues](https://img.shields.io/github/issues-closed/Anuj-Patiyal/hybrid-framework)

### Tech Stack
![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%252B-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.34.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red)
![Log4j2](https://img.shields.io/badge/Log4j2-2.20.0-blueviolet)
![WebDriverManager](https://img.shields.io/badge/WebDriverManager-5.6.4-brightgreen)
![Multi-Browser](https://img.shields.io/badge/Multi--Browser-4%2520Browsers-success)

### Project Health
![Repo Size](https://img.shields.io/github/repo-size/Anuj-Patiyal/hybrid-framework)
![Languages Count](https://img.shields.io/github/languages/count/Anuj-Patiyal/hybrid-framework)
![License](https://img.shields.io/github/license/Anuj-Patiyal/hybrid-framework)
![Maintenance](https://img.shields.io/maintenance/yes/2025)

---

## 📚 Table of Contents
- [🚀 Project Overview](#-project-overview)
- [🏗️ Architecture](#-architecture)
- [✨ Features](#-features)
- [📁 Project Structure](#-project-structure)
- [⚡ Quick Start](#-quick-start)
- [🔧 Configuration](#-configuration)
- [🌿 Branch Strategy](#-branch-strategy)
- [🔄 CI/CD Pipeline](#-cicd-pipeline)
- [📊 Milestones](#-milestones)
- [🤝 Contributing](#-contributing)
- [👨‍💻 Author](#-author)
- [📜 License](#-license)

---

## 🚀 Project Overview
### 🎯 What is This Framework?
A modern, scalable test automation framework built with Java, Selenium WebDriver, and TestNG, designed for enterprise-level testing with comprehensive error handling and reporting.

### 🏆 Current Release: v0.5.0
Advanced Driver Management - Implementing WebDriverManager integration, multi-browser support, and thread-safe driver operations.
```mermaid
timeline
    title Framework Evolution
    section v0.1.0
        Basic Test Setup : Selenium + TestNG
    section v0.2.0
        Configuration Mgmt : External properties
    section v0.3.0
        Logging System : Log4j2 Integration
    section v0.4.0
        Exception Handling: Custom exceptions & error recovery
    section v0.5.0
        Driver Management: WebDriverManager & multi-browser
```
---

## 🏗️ Architecture
### 🔬 High-Level Architecture
```mermaid
graph TB
    subgraph "Test Automation Framework v0.5.0"
        A[Test Scripts] --> B[BaseTest Class]
        B --> C[Driver Factory]
        C --> D[WebDriverManager]
        D --> E[Browser Drivers]
        E --> F[Web Applications]
        
        G[Configuration] --> C
        H[Exception Handler] --> I[Error Recovery]
        J[Logging System] --> K[Structured Logs]
        L[Thread Manager] --> M[Parallel Execution]
        
        B --> H
        B --> J
        B --> L
        C --> N[Multi-Browser Support]
    end
    
    subgraph "Browser Support Matrix"
        O[Chrome] --> P[Full Support]
        Q[Firefox] --> R[Full Support]
        S[Edge] --> T[Full Support]
        U[Safari] --> V[Limited Support]
    end
    
    style C fill:#e3f2fd
    style D fill:#bbdefb
    style N fill:#e8f5e8
```

### 🔄 Driver Management Flow
```mermaid
flowchart TD
    A[Test Execution Start] --> B[BaseTest Setup]
    B --> C[DriverFactory.createDriver]
    C --> D{Select Browser}
    
    D -->|Chrome| E[WebDriverManager Chrome]
    D -->|Firefox| F[WebDriverManager Firefox]
    D -->|Edge| G[WebDriverManager Edge]
    D -->|Safari| H[WebDriverManager Safari]
    
    E --> I[Apply Browser Options]
    F --> I
    G --> I
    H --> I
    
    I --> J[ThreadLocal Storage]
    J --> K[Test Execution]
    K --> L[DriverManager.quitDriver]
    L --> M[ThreadLocal Cleanup]
    
    style C fill:#e3f2fd
    style J fill:#fff3e0
```
---

## ✨ Features
## 🎯 Core Framework Features
| Feature                | Version | Status      | Description                              |
|------------------------|---------|-------------|------------------------------------------|
| Driver Management      | v0.5.0  | ✅ Completed | WebDriverManager with automatic handling |
| Multi-Browser Support  | v0.5.0  | ✅ Completed | Chrome, Firefox, Edge, Safari support    |
| Thread-Safe Operations | v0.5.0  | ✅ Completed | ThreadLocal-based driver management      |
| Abstract Base Test     | v0.5.0  | ✅ Completed | Centralized test setup/teardown          |
| Exception Handling     | v0.4.0  | ✅ Completed | Graceful recovery with custom exceptions |
| Structured Logging     | v0.3.0  | ✅ Completed | Log4j2 for console & file logging        |
| Config Management      | v0.2.0  | ✅ Completed | Externalized `config.properties`         |

### 🌐 Browser Support Matrix
```mermaid
graph LR
    A[Chrome] --> A1[Full Support]
    B[Firefox] --> B1[Full Support]
    C[Edge] --> C1[Full Support]
    D[Safari] --> D1[macOS Only]
    
    E[Headless Mode] --> E1[All Except Safari]
    F[Parallel Execution] --> F1[All Browsers]
    G[Automatic Updates] --> G1[WebDriverManager]
    
    style A1 fill:#c8e6c9
    style B1 fill:#c8e6c9
    style C1 fill:#c8e6c9
    style D1 fill:#ffecb3
```
### 🔧 Technical Specifications
```mermaid
graph LR
    A[Java 21] --> B[Selenium 4]
    B --> C[TestNG 7]
    C --> D[Log4j2]
    D --> E[Maven]
    E --> F[GitHub Actions]
    
    G[Custom Exceptions] --> H[Error Recovery]
    I[Properties Config] --> J[Runtime Config]
    K[HTML Reports] --> L[Test Analytics]
    
    style G fill:#ffebee
    style H fill:#fff3e0
```
---

## 📁 Project Structure
### 🗂️ Complete Directory Layout
```text
hybrid-framework/
├── 📁 src/
│   ├── 📁 main/
│   │   └── 📁 resources/
│   │       └── 🎯 log4j2.xml                 # Logging configuration
│   └── 📁 test/
│       ├── 📁 java/
│       │   ├── 📁 base/                      # 🆕 Base test classes
│       │   │   └── 🎯 BaseTest.java          # Abstract base test class
│       │   ├── 📁 config/
│       │   │   └── 🎯 Configuration.java     # Configuration manager
│       │   ├── 📁 drivers/                   # 🆕 Driver management
│       │   │   ├── 🎯 DriverManager.java     # Thread-safe driver management
│       │   │   └── 🎯 DriverFactory.java     # Multi-browser factory
│       │   ├── 📁 exceptions/                # Exception classes
│       │   │   ├── 🎯 FrameworkException.java
│       │   │   └── 🎯 ElementNotFoundException.java
│       │   └── 📁 tests/
│       │       └── 🎯 TextBoxTest.java       # Test class extending BaseTest
│       └── 📁 resources/
│           └── 🎯 config.properties          # Test configuration
├── 📁 logs/                                  # Generated log files
├── 📁 .github/
│   ├── 📁 workflows/                         # CI/CD pipelines
│   ├── 📁 issues/                            # Issue templates
│   ├── 📁 features/                          # Feature PR templates
│   └── 📁 releases/                          # Release templates
├── 🎯 pom.xml                               # Maven configuration
└── 🎯 README.md                             # Project documentation

```
### 🔍 Driver Management Architecture
```mermaid
graph TB
    subgraph "Driver Management Layer v0.5.0"
        A[BaseTest] --> B[DriverFactory]
        B --> C[WebDriverManager]
        C --> D[Browser Drivers]

        E[DriverManager] --> F[ThreadLocal Storage]
        F --> G[Driver Instances]
        G --> H[Parallel Execution]

        I[Configuration] --> J[Browser Settings]
        J --> K[Chrome Options]
        J --> L[Firefox Options]
        J --> M[Edge Options]
        J --> N[Safari Options]
    end

    subgraph "Supported Browsers"
        O[Chrome] --> P[Auto-Download]
        Q[Firefox] --> R[Auto-Download]
        S[Edge] --> T[Auto-Download]
        U[Safari] --> V[System Driver]
    end

    style B fill:#e3f2fd
    style E fill:#fff3e0
    style C fill:#bbdefb
```
---

## ⚡ Quick Start
### 🚀 5-Minute Setup
```bash
# 1. Clone the repository
git clone https://github.com/Anuj-Patiyal/hybrid-framework.git
cd hybrid-framework

# 2. Run tests with automatic driver setup
mvn clean test

# 3. Check multi-browser support
# Edit config.properties and change browser
```
### 🎛️ Browser Configuration Examples
**Run with Chrome:**
```properties
browser=chrome
headless=true
```
**Run with Firefox:**
```properties
browser=firefox
headless=true
```
**Run with Edge:**
```properties
browser=edge
headless=true
```

### ✅ Prerequisites Checklist
- Java 21 or higher installed
- Maven 3.6+ configured 
- Git for version control 
- Chrome/Firefox browsers available

### 🧪 Test Execution Commands
```bash
# Run all tests with default browser
mvn clean test

# Run with specific browser
mvn test -Dbrowser=firefox

# Run in headed mode for debugging
mvn test -Dheadless=false

# Generate test reports
mvn surefire-report:report
```
---

## 🔧 Configuration
### ⚙️ Configuration Files

**src/test/resources/config.properties**
```properties
# =============================================
# BROWSER CONFIGURATION
# =============================================
browser=chrome
headless=true
window.width=1920
window.height=1080

# =============================================
# TEST ENVIRONMENT
# =============================================
base.url=https://demoqa.com
timeout.explicit=10
timeout.page.load=30
implicit.wait=10

# =============================================
# DRIVER MANAGEMENT
# =============================================
driver.manager.enabled=true
driver.auto.download=true

# =============================================
# LOGGING CONFIGURATION
# =============================================
logging.level=INFO
logging.file=logs/DemoQA.log
```
### 🌐 Multi-Browser Configuration Examples
**Chrome Configuration:**
```properties
browser=chrome
headless=true
chrome.disable.extensions=true
chrome.remote.allow.origins=*
```

**Firefox Configuration:**
```properties
browser=firefox
headless=true
firefox.disable.extensions=true
firefox.log.level=warn
```

**Edge Configuration:**
```properties
browser=edge
headless=true
edge.disable.extensions=true
edge.remote.allow.origins=*
```

### 🔄 Runtime Usage
```java
// In your test classes extending BaseTest
public class YourTest extends BaseTest {
    
    @Test
    public void yourTestMethod() {
        // Driver is automatically available
        driver.get("https://example.com");
        
        // Wait strategy is pre-configured
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element")));
        
        // Navigation utilities available
        navigateTo("/specific-page");
    }
}
```
---

## 🌿 Branch Strategy
### 🔀 Git Workflow
```mermaid
gitGraph
    commit id: "v0.0.0: Initial commit"
    branch dev
    checkout dev
    commit id: "v0.1.0: Basic tests"
    branch feature/config-setup
    commit id: "Add config management"
    checkout dev
    merge feature/config-setup id: "Merge config setup"
    commit id: "v0.2.0: Config setup"
    branch feature/logging
    commit id: "Log4j2 integration"
    checkout dev
    merge feature/logging id: "Merge logging"
    commit id: "v0.3.0: Logging system"
    branch feature/exception-handling
    commit id: "Custom exception framework"
    checkout dev
    merge feature/exception-handling id: "Merge exception handling"
    commit id: "v0.4.0: Exception handling"
    branch feature/driver-management
    commit id: "WebDriverManager integration"
    commit id: "Multi-browser support"
    commit id: "Thread-safe implementation"
    checkout dev
    merge feature/driver-management id: "Merge driver management"
    commit id: "v0.5.0: Driver management"
    checkout main
    merge dev id: "Merge to main"
    tag v0.5.0
```
### 📋 Branch Types

| Branch  | Purpose	            | Naming Convention    | Example                    |
|---------|---------------------|----------------------|---------------------------|
| main    | Production releases | main                 | main                      |
| dev     | Integration branch  | dev                  | dev                       |                                     
| feature | New features        | feature/description  | feature/driver-management | 
| release | Release preparation | release/version      | release/v0.5.0            |
| hotfix  | Critical fixes	    | hotfix/description   | hotfix/driver-fix        |

---

## CI/CD Pipeline
### 🏗️ Enhanced Pipeline Architecture
```mermaid
flowchart TD
    A[Code Commit/Push] --> B{GitHub Event Router}
    
    B -->|Push to main/dev| C[Main CI Pipeline]
    B -->|Any Pull Request| D[POM Validation]
    B -->|PR to feature/*| E[Feature PR Check]
    B -->|CI Success on main| F[Release Automation]
    
    subgraph "Main CI Pipeline v0.5.0"
        C --> C1[Validate POM & Dependencies]
        C1 --> C2[Multi-Browser Setup]
        C2 --> C3[Parallel Test Execution]
        C3 --> C4[Driver Management Verification]
        C4 --> C5[Generate Enhanced Reports]
    end
    
    subgraph "Multi-Browser Testing"
        M1[Chrome Tests] --> M2[Firefox Tests]
        M2 --> M3[Edge Tests]
        M3 --> M4[Test Results Aggregation]
    end
    
    subgraph "Quality Gates v0.5.0"
        Q1[Driver Compatibility] --> Q2[Thread Safety]
        Q2 --> Q3[Multi-Browser Coverage]
        Q3 --> Q4[Performance Metrics]
    end
    
    C5 --> G[Upload Artifacts]
    D --> G
    E --> G
    F --> H[Create Release]
    
    G --> I[Quality Dashboard]
    H --> I
    
    style C2 fill:#e3f2fd
    style C4 fill:#fff3e0
```

### 📊 Pipeline Metrics v0.5.0
| Metric                 | Current    | Target     | Status |
|------------------------|------------|------------|--------|
| Build Time             | ~3m 15s    | < 4m       | ✅     |
| Multi-Browser Coverage | 4 Browsers | 4 Browsers | ✅     |
| Test Pass Rate         | 100%	      | > 95%      | ✅     |
| Parallel Execution     | Ready      | Enabled	   | 🚧     |
| Driver Setup Time      | ~30s       | < 45s	   | ✅     |

---

## 📊 Milestones
### 🎯 Release Timeline
```mermaid
flowchart TD
    A[Code Commit/Push] --> B{GitHub Event Router}
    
    B -->|Push to main/dev| C[Main CI Pipeline]
    B -->|Any Pull Request| D[POM Validation]
    B -->|PR to feature/*| E[Feature PR Check]
    B -->|CI Success on main| F[Release Automation]
    
    subgraph "Main CI Pipeline v0.5.0"
        C --> C1[Validate POM & Dependencies]
        C1 --> C2[Multi-Browser Setup]
        C2 --> C3[Parallel Test Execution]
        C3 --> C4[Driver Management Verification]
        C4 --> C5[Generate Enhanced Reports]
    end
    
    subgraph "Multi-Browser Testing"
        M1[Chrome Tests] --> M2[Firefox Tests]
        M2 --> M3[Edge Tests]
        M3 --> M4[Test Results Aggregation]
    end
    
    subgraph "Quality Gates v0.5.0"
        Q1[Driver Compatibility] --> Q2[Thread Safety]
        Q2 --> Q3[Multi-Browser Coverage]
        Q3 --> Q4[Performance Metrics]
    end
    
    C5 --> G[Upload Artifacts]
    D --> G
    E --> G
    F --> H[Create Release]
    
    G --> I[Quality Dashboard]
    H --> I
    
    style C2 fill:#e3f2fd
    style C4 fill:#fff3e0
```
### 🗓️ Version Roadmap
| Version | Feature            | Status         | Release Date | Progress |
|---------|--------------------|---------------|---------------|----------|
| v0.5.0  | Driver Management  | ✅ Completed   | Oct 26, 2025 | 🟢 100%   |
| v0.6.0  | Page Object Model  | 🔄 In Progress | Oct 30, 2025 | 🟡 20%    |
| v0.7.0  | Wait Utilities     | ⏳ Planned     | Nov 03, 2025 | ⚪ 0%     |
| v0.8.0  | Screenshot Utility | ⏳ Planned     | Nov 07, 2025 | ⚪ 0%     |
| v0.9.0  | TestNG Listeners   | ⏳ Planned     | Nov 11, 2025 | ⚪ 0%     |
| v1.0.0  | Allure Reporting   | ⏳ Planned     | Nov 15, 2025 | ⚪ 0%     |


### 🚀 Upcoming Features
```mermaid
graph LR
    A[v0.5.0 Driver Mgmt] --> B[v0.6.0 POM]
    B --> C[v0.7.0 Wait Utils]
    C --> D[v0.8.0 Screenshots]
    D --> E[v0.9.0 Listeners]
    E --> F[v1.0.0 Reporting]
    
    G[Multi-Browser] --> H[Page Objects]
    H --> I[Smart Waits]
    I --> J[Visual Testing]
    J --> K[Event Handling]
    K --> L[Dashboards]
    
    style A fill:#c8e6c9
    style B fill:#ffecb3
```

---

## 🤝 Contributing
### 🔄 Contribution Workflow
```mermaid
flowchart LR
    A[Fork Repository] --> B[Create Feature Branch]
    B --> C[Implement Features]
    C --> D[Multi-Browser Testing]
    D --> E[Thread Safety Verification]
    E --> F[Create Pull Request]
    F --> G[CI/CD Pipeline]
    G --> H[Code Review]
    H --> I[Merge to Dev]
    
    subgraph "Testing Requirements v0.5.0"
        T1[Chrome Compatibility]
        T2[Firefox Compatibility]
        T3[Edge Compatibility]
        T4[Thread Safety]
        T5[Driver Cleanup]
    end
    
    D --> T1
    D --> T2
    D --> T3
    E --> T4
    E --> T5
    
    style D fill:#e3f2fd
    style E fill:#fff3e0
```
### 📋 Contribution Guidelines
#### ✅ Code Standards
- Follow Java naming conventions
- Use custom exceptions appropriately
- Include comprehensive logging
- Write meaningful commit messages

#### ✅ Testing Requirements
- All new features must include tests
- Exception scenarios must be tested
- Maintain or improve test coverage
- Verify logging output

#### ✅ Documentation
- Update README for new features
- Document exception scenarios
- Include configuration changes
- Update architecture diagrams

#### 🎯 Pull Request Checklist
- Code Quality
- Custom exceptions used appropriately
- Error messages are descriptive
- Resource cleanup implemented
- Logging integrated properly

#### Testing
- All tests pass
- New tests added for features
- Exception scenarios covered
- CI/CD pipeline successful

#### Documentation
- README updated if needed
- Code comments added
- Configuration documented
- Architecture diagrams updated

### 🎯 Pull Request Checklist v0.5.0
- Driver Management
    - WebDriverManager integration working
    - Multi-browser support verified
    - Thread-safe operations implemented
    - Driver cleanup properly handled

- Testing
    - All browsers tested (Chrome, Firefox, Edge)
    - Headless mode verified
    - Parallel execution compatibility
    - Existing tests passing
    
- Code Quality
    - BaseTest extension implemented
    - Configuration properly utilized
    - Exception handling integrated
    - Logging comprehensive
---

## 👤 Author
### 🏆 ANUJ KUMAR
**QA Consultant & Test Automation Architect**
### 📬 Contact Details

| Type             | Details                                                              |
|------------------|----------------------------------------------------------------------|
| 📧 **Email**     | [anujpatiyal@live.in](mailto:anujpatiyal@live.in)                    |
| 🔗 **LinkedIn**  | [Anuj Kumar – QA](https://www.linkedin.com/in/anuj-kumar-qa/)        |
| 🏢 **GitHub**    | [Anuj-Patiyal](https://github.com/Anuj-Patiyal)                      |
| 💼 **Portfolio** | [Hybrid Framework](https://github.com/Anuj-Patiyal/hybrid-framework) |

### 🔧 Technical Expertise
- **Test Automation**           : Selenium, TestNG, WebDriverManager, Multi-Browser
- **Framework Architecture**    : Driver Management, Page Object Model, CI/CD
- **Programming**               : Java 21, Design Patterns, Exception Handling
- **DevOps**                    : GitHub Actions, Maven, Docker, Parallel Execution
- **Quality Engineering**       : Test Strategy, Automation Planning, Code Quality
---

## 📜 License
```text
Copyright (c) 2025 ANUJ KUMAR

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

### 💡 **Open Source Philosophy**
*"First, solve the problem. Then, write the code."* – John Johnson

This framework embodies robust engineering principles with enterprise-grade exception handling and structured logging for reliable test automation.

<div align="center">

🚀 **Ready to automate with confidence?**
⭐ **Star this repository if you find it helpful!**

[🌟 Star on GitHub](https://github.com/Anuj-Patiyal/hybrid-framework) • [🐛 Report Issue](https://github.com/Anuj-Patiyal/hybrid-framework/issues) • [💡 Request Feature](https://github.com/Anuj-Patiyal/hybrid-framework/discussions)

</div>
---

