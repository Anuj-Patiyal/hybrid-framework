# 🚀 Hybrid Framework
> **Enterprise-grade test automation framework with robust exception handling, structured logging, and comprehensive CI/CD**
---
## 📦 Version & Activity
[![](https://img.shields.io/github/v/release/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/releases)
[![](https://img.shields.io/github/release-date/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/releases)
[![](https://img.shields.io/github/last-commit/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/commits)
[![](https://img.shields.io/github/commit-activity/m/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/commits)

---

## ✅ CI/CD & Quality
[![](https://img.shields.io/github/actions/workflow/status/Anuj-Patiyal/hybrid-framework/main-ci.yml?label=Main%20CI)](https://github.com/Anuj-Patiyal/hybrid-framework/actions/workflows/main-ci.yml)
[![](https://img.shields.io/github/actions/workflow/status/Anuj-Patiyal/hybrid-framework/pom-validation.yml?label=Pom%20Validation)](https://github.com/Anuj-Patiyal/hybrid-framework/actions/workflows/pom-validation.yml)
[![](https://img.shields.io/github/issues/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/issues)
[![](https://img.shields.io/github/issues-closed/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/issues?q=is%3Aissue+is%3Aclosed)

---

## 🛠 Tech Stack
![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%252B-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.34.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red)
![Log4j2](https://img.shields.io/badge/Log4j2-2.20.0-blueviolet)
![Exception Handling](https://img.shields.io/badge/Exception--Handling-Implemented-success)

---

## 📊 Project Health
[![](https://img.shields.io/github/repo-size/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework)
[![](https://img.shields.io/github/languages/count/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework)
[![](https://img.shields.io/github/license/Anuj-Patiyal/hybrid-framework)](https://github.com/Anuj-Patiyal/hybrid-framework/blob/main/LICENSE)
![Maintenance](https://img.shields.io/maintenance/yes/2025)

---

## 📚 Table of Contents
1. 🚀 [Project Overview](#-project-overview)
2. 🏗️ [Architecture](#-architecture)
3. ✨ [Features](#-features)
4. 📁 [Project Structure](#-project-structure)
5. ⚡ [Quick Start](#-quick-start)
6. 🔧 [Configuration](#-configuration)
7. 🌿 [Branch Strategy](#-branch-strategy)
8. 🔄 [CI/CD Pipeline](#-cicd-pipeline)
9. 📊 [Milestones](#-milestones)
10. 🤝 [Contributing](#-contributing)
11. 👨‍💻 [Author](#-author)
12. 📜 [License](#-license)

---

## 🚀 Project Overview
### 🎯 What is This Framework?
A modern, scalable test automation framework built with Java, Selenium WebDriver, and TestNG, designed for enterprise-level testing with comprehensive error handling and reporting.

### 🏆 Current Release: v0.4.0
Robust Exception Handling Framework - Implementing structured error management across all framework components.
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
```
---

## 🏗️ Architecture
### 🔬 High-Level Architecture
```mermaid
graph TB
    subgraph "Test Automation Framework"
        A[Test Scripts] --> B[TestNG Framework]
        B --> C[Exception Handler]
        C --> D[Configuration Manager]
        D --> E[Logging System]
        E --> F[WebDriver Factory]
        F --> G[Browser Drivers]
        G --> H[Web Applications]
        
        I[Custom Exceptions] --> C
        J[Properties Files] --> D
        K[Log4j2 Config] --> E
        L[CI/CD Pipeline] --> M[GitHub Actions]
    end
    
    subgraph "Supporting Systems"
        N[Maven Build] --> O[Dependency Mgmt]
        P[Git] --> Q[Version Control]
        R[Reports] --> S[Test Analytics]
    end
    
    style C fill:#ffebee
    style E fill:#e8f5e8
    style I fill:#fff3e0
```
### 🔄 Data Flow Architecture
```mermaid
flowchart TD
    A[Test Execution Start] --> B[Load Configuration]
    B --> C[Initialize Logging]
    C --> D[Setup WebDriver]
    D --> E[Execute Test Steps]
    E --> F{Check Success}
    
    F -->|Success| G[Log Results]
    F -->|Failure| H[Handle Exception]
    
    H --> I[Log Error Details]
    I --> J[Capture Context]
    J --> K[Cleanup Resources]
    K --> L[Generate Report]
    
    G --> L
    L --> M[CI/CD Integration]
    
    style H fill:#ffebee
    style I fill:#fff3e0
```
---

## ✨ Features
## 🎯 Core Framework Features

| Feature                      | Version | Status      | Description                                    |
|------------------------------|---------|-------------|------------------------------------------------|
| **Exception Handling**       | v0.4.0  | ✅ Completed | Custom exceptions with graceful error recovery |
| **Structured Logging**       | v0.3.0  | ✅ Completed | Log4j2 with console & file appenders           |
| **Configuration Management** | v0.2.0  | ✅ Completed | Externalized properties file support           |
| **TestNG Integration**       | v0.1.0  | ✅ Completed | Test execution and reporting                   |
| **CI/CD Pipeline**           | v0.0.0  | ✅ Completed | GitHub Actions automation                      |

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
│       │   ├── 📁 config/
│       │   │   └── 🎯 Configuration.java     # Configuration manager
│       │   ├── 📁 exceptions/                # 🆕 Exception classes
│       │   │   ├── 🎯 FrameworkException.java
│       │   │   └── 🎯 ElementNotFoundException.java
│       │   └── 📁 tests/
│       │       └── 🎯 TextBoxTest.java       # Test with exception handling
│       └── 📁 resources/
│           └── 🎯 config.properties          # Test configuration
├── 📁 logs/                                  # Generated log files
├── 📁 .github/
│   ├── 📁 workflows/
│   │   ├── 🎯 main-ci.yml                   # CI pipeline
│   │   ├── 🎯 feature-pr.yml               # Feature validation
│   │   ├── 🎯 release-pr.yml               # Release automation
│   │   └── 🎯 pom-validation.yml           # POM validation
│   ├── 📁 issues/                           # Issue templates
│   ├── 📁 features/                         # Feature PR templates
│   └── 📁 releases/                         # Release templates
├── 🎯 pom.xml                               # Maven configuration
└── 🎯 README.md                             # Project documentation
```
### 🔍 Key Components Deep Dive
```mermaid
graph TB
    subgraph "Configuration Layer"
        A[config.properties] --> B[Configuration.java]
        B --> C[Runtime Settings]
    end
    
    subgraph "Exception Layer"
        D[FrameworkException] --> E[ElementNotFoundException]
        D --> F[Future Exceptions]
    end
    
    subgraph "Logging Layer"
        G[log4j2.xml] --> H[Logger Instances]
        H --> I[Console Appender]
        H --> J[File Appender]
    end
    
    subgraph "Test Layer"
        K[TextBoxTest.java] --> L[Test Methods]
        L --> M[Exception Handling]
        L --> N[Logging Integration]
    end
    
    style D fill:#ffebee
    style E fill:#fff3e0
```
---

## ⚡ Quick Start
### 🚀 5-Minute Setup
```bash
# 1. Clone the repository
git clone https://github.com/Anuj-Patiyal/hybrid-framework.git
cd hybrid-framework

# 2. Verify setup
mvn --version
java --version

# 3. Run your first test
mvn clean test

# 4. Check results
ls -la logs/
cat logs/DemoQA.log
```
### ✅ Prerequisites Checklist
- Java 21 or higher installed
- Maven 3.6+ configured 
- Git for version control 
- Chrome/Firefox browsers available

### 🧪 Test Execution
```bash
# Run all tests
mvn clean test

# Run with specific profile
mvn test -Pci

# Generate reports
mvn surefire-report:report
```
---

## 🔧 Configuration
### ⚙️ Configuration Files
src/test/resources/config.properties
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

# =============================================
# LOGGING CONFIGURATION
# =============================================
logging.level=INFO
logging.file=logs/DemoQA.log
```
### 🎛️ Runtime Configuration
```java
// Access configuration in tests
String browser = Configuration.getBrowser();
boolean headless = Configuration.isHeadless();
String baseUrl = Configuration.getBaseUrl();

// Example usage with exception handling
try {
    driver.get(Configuration.getBaseUrl() + "/text-box");
} catch (FrameworkException e) {
    logger.error("Failed to navigate to URL: {}", e.getMessage());
    throw e;
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
    checkout main
    merge dev id: "Merge to main"
    tag v0.4.0
```
### 📋 Branch Types

| Branch Type | Purpose                    | Naming Convention        |
|-------------|----------------------------|---------------------------|
| **main**    | Production-ready releases  | `main`                    |
| **dev**     | Integration branch         | `dev`                     |
| **feature** | New features               | `feature/<description>`   |
| **release** | Release preparation        | `release/<version>`       |
| **hotfix**  | Critical fixes             | `hotfix/<description>`    |

---

## 🔄 CI/CD Pipeline
### 🏗️ Pipeline Architecture
```mermaid
flowchart TD
    A[Code Commit/Push] --> B{GitHub Event Router}
    
    B -->|Push to main/dev| C[Main CI Pipeline]
    B -->|Any Pull Request| D[POM Validation]
    B -->|PR to feature/*| E[Feature PR Check]
    B -->|CI Success on main| F[Release Automation]
    
    subgraph "Main CI Pipeline"
        C --> C1[Validate POM]
        C1 --> C2[Build & Compile]
        C2 --> C3[Run Tests]
        C3 --> C4[Exception Handling Check]
        C4 --> C5[Generate Reports]
    end
    
    subgraph "Quality Gates"
        Q1[Code Quality] --> Q2[Test Coverage]
        Q2 --> Q3[Exception Coverage]
        Q3 --> Q4[Logging Verification]
    end
    
    C5 --> G[Upload Artifacts]
    D --> G
    E --> G
    F --> H[Create Release]
    
    G --> I[Quality Dashboard]
    H --> I
    
    style C4 fill:#ffebee
    style Q3 fill:#fff3e0
```
### 📊 Pipeline Metrics

| Metric                 | Current | Target | Status |
|------------------------|---------|--------|--------|
| **Build Time**         | ~2m 30s | < 3m   | ✅      |
| **Test Pass Rate**     | 100%    | > 95%  | ✅      |
| **Exception Coverage** | 100%    | 100%   | ✅      |
| **Validation Checks**  | 28      | 30+    | 🚧     |

---

## 📊 Milestones
### 🎯 Release Timeline
```mermaid
gantt
    title Framework Development Timeline
    dateFormat  YYYY-MM-DD
    axisFormat %b %d
    
    section Completed Releases
    v0.0.0 :done, milestone1, 2025-10-06, 0d
    v0.1.0 :done, milestone2, 2025-10-10, 0d
    v0.2.0 :done, milestone3, 2025-10-14, 0d
    v0.3.0 :done, milestone4, 2025-10-18, 0d
    v0.4.0 :done, milestone5, 2025-10-22, 0d
    
    section In Development
    v0.5.0 :active, milestone6, 2025-10-26, 0d
    
    section Planned Releases
    v0.6.0 :milestone7, 2025-10-30, 0d
    v0.7.0 :milestone8, 2025-11-03, 0d
    v0.8.0 :milestone9, 2025-11-07, 0d
    v0.9.0 :milestone10, 2025-11-11, 0d
    v1.0.0 :milestone11, 2025-11-15, 0d
```
### 🗓️ Version Roadmap
| Version | Feature            | Status         | Release Date | Progress |
|---------|--------------------|----------------|--------------|----------|
| v0.4.0  | Exception Handling | ✅ Completed    | Oct 22, 2025 | 🟢 100%  |
| v0.5.0  | Driver Management  | 🔄 In Progress | Oct 26, 2025 | 🟡 40%   |
| v0.6.0  | Page Object Model  | ⏳ Planned      | Oct 30, 2025 | ⚪ 0%     |
| v0.7.0  | Wait Utilities     | ⏳ Planned      | Nov 03, 2025 | ⚪ 0%     |
| v0.8.0  | Screenshot Utility | ⏳ Planned      | Nov 07, 2025 | ⚪ 0%     |
| v0.9.0  | TestNG Listeners   | ⏳ Planned      | Nov 11, 2025 | ⚪ 0%     |
| v1.0.0  | Allure Reporting   | ⏳ Planned      | Nov 15, 2025 | ⚪ 0%     |

---

## 🤝 Contributing
### 🔄 Contribution Workflow
```mermaid
flowchart LR
    A[Fork Repository] --> B[Create Feature Branch]
    B --> C[Implement Features]
    C --> D[Add Exception Handling]
    D --> E[Write Tests]
    E --> F[Run Validations]
    F --> G[Create Pull Request]
    G --> H[CI/CD Pipeline]
    H --> I[Code Review]
    I --> J[Merge to Dev]
    
    subgraph "Validation Suite"
        V1[POM Validation]
        V2[Test Execution]
        V3[Exception Coverage]
        V4[Logging Verification]
    end
    
    F --> V1
    F --> V2
    F --> V3
    F --> V4
    
    style D fill:#ffebee
    style V3 fill:#fff3e0
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
---

## 👤 Author
### 🏆 ANUJ KUMAR
**QA Consultant & Test Automation Architect**

---

### 📬 Contact Details

| Type             | Details                                                              |
|------------------|----------------------------------------------------------------------|
| 📧 **Email**     | [anujpatiyal@live.in](mailto:anujpatiyal@live.in)                    |
| 🔗 **LinkedIn**  | [Anuj Kumar – QA](https://www.linkedin.com/in/anuj-kumar-qa/)        |
| 🏢 **GitHub**    | [Anuj-Patiyal](https://github.com/Anuj-Patiyal)                      |
| 💼 **Portfolio** | [Hybrid Framework](https://github.com/Anuj-Patiyal/hybrid-framework) |


---

## 📜 License
```text
Copyright (c) 2025 Anuj Kumar

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

```
---

