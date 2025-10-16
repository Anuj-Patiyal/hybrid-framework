# 🚀 Hybrid Framework
**Advanced Page Object Model - (v0.6.0)**
> Enterprise-grade test automation framework with Page Object Model, advanced driver management, and multi-browser support

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
![Page Object Model](https://img.shields.io/badge/POM-Implemented-success)

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
A modern, scalable test automation framework built with Java, Selenium WebDriver, and TestNG, implementing Page Object Model pattern for enterprise-level testing with comprehensive error handling and reporting.

### 🏆 Current Release: v0.6.0
Page Object Model Implementation - Implementing robust POM pattern, page factory, and reusable page components.
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
    section v0.6.0
        Page Object Model: POM pattern & page factory
```
---

## 🏗️ Architecture
### 🔬 High-Level Architecture
```mermaid
graph TB
    subgraph "Test Automation Framework v0.6.0"
        A[Test Scripts] --> B[Page Objects]
        B --> C[BasePage Class]
        C --> D[BaseTest Class]
        D --> E[Driver Factory]
        E --> F[WebDriverManager]
        F --> G[Browser Drivers]
        G --> H[Web Applications]
        
        I[Configuration] --> E
        J[Exception Handler] --> K[Error Recovery]
        L[Logging System] --> M[Structured Logs]
        N[Thread Manager] --> O[Parallel Execution]
        
        B --> P[Page Factory]
        C --> Q[Common Page Actions]
        D --> J
        D --> L
        D --> N
        E --> R[Multi-Browser Support]
    end
    
    subgraph "Page Object Model Layer"
        S[LoginPage] --> T[HomePage]
        U[ProductsPage] --> V[CartPage]
        W[CheckoutPage] --> X[ConfirmationPage]
    end
    
    style B fill:#e8f5e8
    style C fill:#e3f2fd
    style P fill:#fff3e0
```

### 🔄 Page Object Model Flow
```mermaid
flowchart TD
    A[Test Execution Start] --> B[BaseTest Setup]
    B --> C[Initialize Page Objects]
    C --> D[Page Factory Initialization]
    D --> E[Lazy Loading Elements]
    
    E --> F{Page Actions}
    F -->|Navigation| G[Page Navigation Methods]
    F -->|Validation| H[Page Validation Methods]
    F -->|Interaction| I[Element Interaction Methods]
    
    G --> J[URL Management]
    H --> K[Assertion Framework]
    I --> L[WebElement Wrappers]
    
    J --> M[Test Execution]
    K --> M
    L --> M
    
    M --> N[Page Transition]
    N --> O[New Page Object Creation]
    O --> P[Continue Test Flow]
    
    style C fill:#e8f5e8
    style D fill:#e3f2fd
    style G fill:#fff3e0
```
---

## ✨ Features
## 🎯 Core Framework Features
| Feature                | Version | Status       | Description                              |
|------------------------|---------|--------------|------------------------------------------|
| Page Object Model      | v0.6.0  | ✅ Completed | Robust POM pattern with page factory      |
| Driver Management      | v0.5.0  | ✅ Completed | WebDriverManager with automatic handling |
| Multi-Browser Support  | v0.5.0  | ✅ Completed | Chrome, Firefox, Edge, Safari support    |
| Thread-Safe Operations | v0.5.0  | ✅ Completed | ThreadLocal-based driver management      |
| Abstract Base Test     | v0.5.0  | ✅ Completed | Centralized test setup/teardown          |
| Exception Handling     | v0.4.0  | ✅ Completed | Graceful recovery with custom exceptions |
| Structured Logging     | v0.3.0  | ✅ Completed | Log4j2 for console & file logging        |
| Config Management      | v0.2.0  | ✅ Completed | Externalized config.properties           |

### 📄 Page Object Model Features
```mermaid
graph LR
    A[BasePage Class] --> B[Common Utilities]
    C[Page Factory] --> D[Lazy Element Loading]
    E[Page Navigation] --> F[URL Management]
    G[Element Wrappers] --> H[Smart Interactions]
    I[Validation Methods] --> J[Assertion Framework]
    K[Page Components] --> L[Reusable Components]
    
    style A fill:#e8f5e8
    style C fill:#e3f2fd
    style G fill:#fff3e0
```

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
    H[POM Support] --> H1[All Browsers]
    
    style A1 fill:#c8e6c9
    style B1 fill:#c8e6c9
    style C1 fill:#c8e6c9
    style D1 fill:#ffecb3
    style H1 fill:#bbdefb
```
### 🔧 Technical Specifications
```mermaid
graph LR
    A[Java 21] --> B[Selenium 4]
    B --> C[TestNG 7]
    C --> D[Log4j2]
    D --> E[Maven]
    E --> F[GitHub Actions]
    
    G[Page Object Model] --> H[Page Factory]
    I[Custom Exceptions] --> J[Error Recovery]
    K[Properties Config] --> L[Runtime Config]
    M[HTML Reports] --> N[Test Analytics]
    
    style G fill:#e8f5e8
    style H fill:#e3f2fd
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
│       │   ├── 📁 base/                      # Base test classes
│       │   │   └── 🎯 BaseTest.java          # Abstract base test class
│       │   ├── 📁 config/
│       │   │   └── 🎯 Configuration.java     # Configuration manager
│       │   ├── 📁 drivers/                   # Driver management
│       │   │   ├── 🎯 DriverManager.java     # Thread-safe driver management
│       │   │   └── 🎯 DriverFactory.java     # Multi-browser factory
│       │   ├── 📁 exceptions/                # Exception classes
│       │   │   ├── 🎯 FrameworkException.java
│       │   │   └── 🎯 ElementNotFoundException.java
│       │   ├── 📁 pages/                     # 🆕 Page Object Model
│       │   │   ├── 🎯 BasePage.java          # Abstract base page class
│       │   │   ├── 🎯 LoginPage.java         # Login page implementation
│       │   │   ├── 🎯 HomePage.java          # Home page implementation
│       │   │   └── 🎯 ProductsPage.java      # Products page implementation
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
### 🔍 Page Object Model Architecture
```mermaid
graph TB
    subgraph "Page Object Model Layer v0.6.0"
        A[BaseTest] --> B[Page Objects]
        B --> C[BasePage]
        C --> D[Page Factory]
        D --> E[WebElement Initialization]
        
        F[Common Page Actions] --> G[Navigation Utilities]
        F --> H[Wait Strategies]
        F --> I[Element Interactions]
        
        J[Page Specific Methods] --> K[Business Logic]
        J --> L[Validation Logic]
        J --> M[Data Entry Methods]
        
        N[Test Classes] --> O[Page Object Usage]
        O --> P[Method Chaining]
        P --> Q[Fluent Interface]
    end

    subgraph "Page Object Benefits"
        R[Maintainability] --> S[Reduced Code Duplication]
        T[Reusability] --> U[Cross-Test Page Usage]
        V[Readability] --> W[Business Language Tests]
        X[Scalability] --> Y[Easy New Page Addition]
    end

    style B fill:#e8f5e8
    style C fill:#e3f2fd
    style F fill:#fff3e0
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
    branch feature/page-object-model
    commit id: "BasePage implementation"
    commit id: "Page factory integration"
    commit id: "Page component patterns"
    checkout dev
    merge feature/page-object-model id: "Merge POM"
    commit id: "v0.6.0: Page Object Model"
    checkout main
    merge dev id: "Merge to main"
    tag v0.6.0
```
### 📋 Branch Types

| Branch  | Purpose	            | Naming Convention    | Example                   |
|---------|---------------------|----------------------|---------------------------|
| main    | Production releases | main                 | main                      |
| dev     | Integration branch  | dev                  | dev                       |
| feature | New features        | feature/description  | feature/pom               | 
| release | Release preparation | release/version      | release/v0.6.0            |
| hotfix  | Critical fixes	    | hotfix/description   | hotfix/driver-fix         |

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
    
    subgraph "Main CI Pipeline v0.6.0"
        C --> C1[Validate POM & Dependencies]
        C1 --> C2[Multi-Browser Setup]
        C2 --> C3[Page Object Model Tests]
        C3 --> C4[POM Architecture Validation]
        C4 --> C5[Generate Enhanced Reports]
    end
    
    subgraph "Page Object Model Testing"
        M1[BasePage Tests] --> M2[Page Factory Tests]
        M2 --> M3[Page Navigation Tests]
        M3 --> M4[POM Integration Tests]
    end
    
    subgraph "Quality Gates v0.6.0"
        Q1[POM Implementation] --> Q2[Page Factory Usage]
        Q2 --> Q3[Element Initialization]
        Q3 --> Q4[Method Chaining Validation]
    end
    
    C5 --> G[Upload Artifacts]
    D --> G
    E --> G
    F --> H[Create Release]
    
    G --> I[Quality Dashboard]
    H --> I
    
    style C3 fill:#e8f5e8
    style C4 fill:#e3f2fd
```

### 📊 Pipeline Metrics v0.5.0
| Metric                 | Current    | Target     | Status |
|------------------------|------------|------------|--------|
| Build Time             | ~3m 45s    | < 5m       | ✅     |
| Multi-Browser Coverage | 4 Browsers | 4 Browsers | ✅     |
| Test Pass Rate         | 100%       | > 95%      | ✅     |
| POM Implementation     | Complete   | 100%       | ✅     |
| Code Maintainability   | Improved   | High       | ✅     |
---

## 📊 Milestones
### 🎯 Release Timeline
```mermaid
timeline
    title Release Timeline v0.1.0 - v1.0.0
    section v0.1.0 - Completed
        Basic Framework : Selenium + TestNG Setup
    section v0.2.0 - Completed  
        Configuration : External Properties
    section v0.3.0 - Completed
        Logging System : Log4j2 Integration
    section v0.4.0 - Completed
        Exception Handling : Custom Exceptions
    section v0.5.0 - Completed
        Driver Management : WebDriverManager
    section v0.6.0 - Current
        Page Object Model : POM Pattern
    section v0.7.0 - Next
        Wait Utilities : Smart Wait Strategies
    section v0.8.0 - Planned
        Screenshot Utility : Visual Testing
    section v0.9.0 - Planned
        TestNG Listeners : Enhanced Reporting
    section v1.0.0 - Planned
        Allure Reporting : Dashboard Analytics
```
### 🗓️ Version Roadmap
| Version | Feature             | Status         | Release Date | Progress |
|---------|---------------------|----------------|---------------|---------|
| v0.6.0  | Page Object Model   | ✅ Completed   | Oct 30, 2025  | 🟢 100%  |
| v0.7.0  | Wait Utilities      | 🔄 In Progress | Nov 03, 2025  | 🟡 40%   |
| v0.8.0  | Screenshot Utility  | ⏳ Planned	     | Nov 07, 2025  | ⚪ 0%    |
| v0.9.0  | TestNG Listeners    | ⏳ Planned     | Nov 11, 2025  | ⚪ 0%    |
| v1.0.0  | Allure Reporting    | ⏳ Planned	     | Nov 15, 2025  | ⚪ 0%    |

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
    
    style B fill:#c8e6c9
    style C fill:#ffecb3
```

---

## 🤝 Contributing
### 🔄 Contribution Workflow
```mermaid
flowchart LR
    A[Fork Repository] --> B[Create Feature Branch]
    B --> C[Implement Features]
    C --> D[Page Object Model Testing]
    D --> E[POM Pattern Verification]
    E --> F[Create Pull Request]
    F --> G[CI/CD Pipeline]
    G --> H[Code Review]
    H --> I[Merge to Dev]
    
    subgraph "POM Requirements v0.6.0"
        T1[BasePage Extension]
        T2[Page Factory Usage]
        T3[Element Initialization]
        T4[Method Chaining]
        T5[Fluent Interface]
    end
    
    D --> T1
    D --> T2
    D --> T3
    E --> T4
    E --> T5
    
    style D fill:#e8f5e8
    style E fill:#e3f2fd
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

