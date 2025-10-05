---
title: "🎯 Initial framework setup (Maven + CICD)"
milestone: v0.0.0
labels: maven, dependencies, plugings, foundation, ci-cd
assignees: Anuj-Patiyal
---

## 📌 Description
Initialize the foundational Maven project structure for the hybrid test automation framework with integrated CI/CD pipeline. This establishes the build management, core dependencies, basic directory layout, GitHub Actions workflows, and comprehensive project documentation, serving as the cornerstone for all subsequent development.

## ✅ Acceptance Criteria
- [x] A new Maven project is initialized with a correctly structured and validated `pom.xml`
- [x] All specified dependencies are included with their correct versions
- [x] All specified plugins are configured with their correct versions and settings
- [x] A standard `.gitignore` file for Java/Maven/IDE is present and functional
- [x] A comprehensive `README.md` is added, containing all outlined sections
- [x] The basic source directory structure is created and follows standard Maven conventions
- [x] GitHub Actions CI/CD workflows are configured for automated validation
- [x] The project can be successfully compiled using `mvn clean compile`

## 🛠 Required Dependencies & Plugins
**Core Dependencies:**
- [x] `org.seleniumhq.selenium:selenium-java` (v4.34.0)
- [x] `org.testng:testng` (v7.11.0)

**Maven Plugins:**
- [x] `org.apache.maven.plugins:maven-compiler-plugin` (v3.14.0)
Configured to use Java **21** as both source and target version
- [x] `org.apache.maven.plugins:maven-clean-plugin` (v3.5.0)
- [x] `org.apache.maven.plugins:maven-surefire-plugin` (v3.2.5) - For TestNG execution
- [x] `org.apache.maven.plugins:maven-jar-plugin` (v3.3.0)
- [x] `org.apache.maven.plugins:maven-install-plugin` (v3.1.1)
- [x] `org.apache.maven.plugins:maven-resources-plugin` (v3.3.1)

## 📁 Expected Project Structure
```text
java-selenium-hybrid-framework/
├── .github/
│ └── workflows/# GitHub Actions CI/CD workflows
│ ├── main-ci.yml # Main CI pipeline
│ └── pom-validation.yml# POM validation workflow
│ └── feature-pr.yml# 
│ └── release-pr.yml# 
├── src/
│ ├── main/java/# For framework core classes and utilities
│ └── test/java/# For test classes, runners, and suites
├── .gitignore# Excludes target/, .idea/, *.iml, etc.
├── pom.xml # Project Object Model - Core configuration
└── README.md # Project documentation
```

## 🔧 CI/CD Configuration
**GitHub Actions Workflows:**
- **Main CI Pipeline:** Automated build, test, and validation on PR/push
- **POM Validation:** XML validation and dependency checks
- **Branch Protection:** Quality gates for main/dev branches

**Quality Gates:**
- Maven compilation validation
- POM XML structure validation
- Automated testing execution
- Dependency resolution checks

## 📋 Implementation Notes
### The `README.md` includes:
- Framework Overview & Goals
- Technology Stack (Java 21, Selenium 4.34.0, TestNG 7.11.0, Maven 3.6+)
- Complete setup instructions
- CI/CD pipeline documentation
- Milestone roadmap and versioning strategy
- Author & license information

### CI/CD pipeline provides:
- Automated testing on all pull requests
- POM validation and dependency checks
- Build status reporting
- Quality gate enforcement

## 🔄 Related CI/CD Components
- Branch Strategy: main/dev with feature branches
- Quality Gates: Required status checks for merging
- Automated Testing: Test execution on every commit
- Validation: POM structure and dependency validation

## 📊 Milestone Progress
- Status: ✅ Completed
- Version: 0.0.0
- CI Status: ![CI Status](https://github.com/Anuj-Patiyal/java-selenium-hybrid-framework/actions/workflows/main-ci.yml/badge.svg)

## 📝 Note
This initial setup establishes the foundation for the hybrid framework with integrated CI/CD, enabling rapid development and quality assurance for subsequent milestones including v0.1.0 (First simple test) and v0.2.0 (Configuration setup).

