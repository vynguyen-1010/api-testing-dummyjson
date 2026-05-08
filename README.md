# API Automation Testing Framework

## Overview

This project is an enterprise-style API automation testing framework built using:
* Java 17
* Rest Assured
* Maven
* TestNG
* Extent Reports
* Jenkins
* GitHub Actions

Target API: https://dummyjson.com

The framework is designed to simulate a real-world automation testing project with:
* scalable architecture
* maintainable code structure
* reusable components
* CI/CD integration
* secure configuration management

The project focuses mainly on User APIs and covers:
* GET
* POST
* PUT
* PATCH
* DELETE

with both positive and negative test scenarios.

---

# Framework Architecture

This framework follows a layered architecture inspired by:
* Service Layer Pattern
* Factory Pattern
* Singleton Pattern
* Builder Pattern
* Separation of Concerns principle

The goal is to make the framework:
* scalable
* reusable
* easy to maintain
* easy to extend for new APIs/modules

---

# Design Patterns Used

## 1. Service Layer Pattern
Purpose: Separate API interaction logic from test logic.

Example:
```text id="9awjlwm"
tests → services → endpoints
```

Benefits:
* reusable API calls
* cleaner tests
* easier maintenance

---

## 2. Factory Pattern

Used in:
```text id="yzm8j3"
RequestSpecFactory
```

Purpose: Centralize creation of request specifications.

Benefits:
* avoid duplicated configuration
* easier environment management
* easier logging integration

---

## 3. Singleton Pattern

Used in:
```text id="12shhj"
RequestSpecification
ExtentReports
```

Purpose: Ensure only one shared instance exists.

Benefits:
* memory efficient
* consistent configuration
* thread-safe resource management

---

## 4. Builder Pattern

Used by:
```text id="w0vz17"
RequestSpecBuilder
```

Purpose: Build complex request configurations cleanly.

Benefits:
* readable configuration
* flexible setup
* scalable request customization

---

# Project Structure

```text id="8ru0go"
src
├── main
│   └── java
│       ├── config
│       ├── constants
│       ├── core
│       ├── endpoints
│       ├── models
│       ├── services
│       └── utils
│
├── test
│   ├── java
│   │   ├── assertions
│   │   ├── listeners
│   │   ├── retry
│   │   └── tests
│   │
│   └── resources
│       └── suites
```

---

# Folder Structure Explanation

## config

Contains framework configuration management.

### Classes
| Class         | Responsibility                                   |
| ------------- | ------------------------------------------------ |
| EnvReader     | Read environment variables from system or `.env` |
| ConfigManager | Centralized configuration access                 |

Purpose: environment isolation, secure configuration handling, CI/CD compatibility

---

## constants

Stores reusable constants. Examples: status codes, API paths, default values
Purpose: avoid magic numbers, improve maintainability

---

## core

Core framework components.

### Classes

| Class              | Responsibility                        |
| ------------------ | ------------------------------------- |
| BaseTest           | Base setup for all tests              |
| RequestSpecFactory | Create reusable request specification |

Purpose: entralized framework setup, reusable request configuration

---

## endpoints

Contains API endpoint definitions.
Example:
```java id="s5it6d"
public static final String GET_USER = "/users/{id}";
```

Purpose: centralized endpoint management, avoid hardcoded URLs

---

## models

POJO models for request/response serialization.
Examples:, User, CreateUserRequest

Purpose: object mapping, cleaner request/response handling, easier assertions
Jackson is used for JSON serialization/deserialization.

---

## services

Contains reusable API methods.
Example:
```java id="eoz7r2"
getUser()
createUser()
updateUser()
```

Purpose:isolate API logic, improve test readability, reusable business actions

---

## utils

Contains utility/helper classes.
Examples:
| Utility       | Purpose                   |
| ------------- | ------------------------- |
| DataGenerator | Generate random test data |
| LoggerUtils   | Request/response logging  |
| MaskingUtils  | Hide sensitive data       |
| ExtentManager | Reporting management      |

Purpose: reusable utilities, framework scalability

---

## assertions

Reusable assertion layer.
Purpose: centralized validations, cleaner test classes, reusable assertion logic

Example:
```java id="tbfg6v"
UserAssertions.verifyUser(...)
```

---

## listeners

Contains TestNG listeners.
Purpose:, reporting integration, test lifecycle management, automatic logging

---

## retry

Contains retry mechanism for flaky tests.
Purpose: reduce false negative failures, handle transient instability

---

## tests

Actual test implementation.
Structure recommendation:
```text id="c5vg4z"
tests
├── user
├── auth
├── card
└── product
```

Purpose: module separation, scalable organization

---

## suites

Contains TestNG suite XML files.
Examples:

```text id="9d85dx"
smoke.xml
regression.xml
user-suite.xml
```

Purpose: selective execution, CI/CD flexibility, module-based execution

---

# Features

## API Testing

* GET
* POST
* PUT
* PATCH
* DELETE

---

## Positive & Negative Testing

Includes:
* valid requests
* invalid requests
* missing fields
* invalid IDs
* edge cases

---

## Dynamic Test Data

Uses Faker library for:
* random names
* emails
* realistic data generation

Benefits: avoid duplicate data, improve test stability

---

## Logging & Masking

Framework supports request/response logging.
Sensitive data is masked automatically:
* password
* token
* email

Logging can be enabled/disabled via environment variable.

---

## Reporting

Uses Extent Reports.
Features:
* HTML reports
* pass/fail status
* stack traces
* execution details

Report location:
```text id="e3f9h7"
reports/extent-report.html
```

---

## Parallel Execution

Supports parallel test execution using TestNG.

Benefits: faster execution, scalable regression testing

---

## Retry Mechanism

Automatic retry for flaky tests.
Purpose: reduce temporary failures, improve CI stability

---

# Environment Management

The framework supports multiple environments.
Examples:
* dev
* staging
* production

Environment variables are prioritized as:
```text id="w7sl04"
System Environment Variables
↓
.env fallback
```

Benefits: secure CI/CD integration, flexible environment switching

---

# Setup Instructions

## Clone Repository

```bash id="6bgfmc"
git clone https://github.com/vynguyen-1010/api-testing-dummyjson.git
```

---

## Install Dependencies

```bash id="h2dd0y"
mvn clean install
```

---

# Environment Variables

Create `.env` file:
```env id="0pws8h"
BASE_URL=https://dummyjson.com
ENABLE_LOG=true
```

---

# Running Tests

## Run all tests

```bash id="v3z4bi"
mvn clean test
```

---

## Run specific suite

```bash id="go9m53"
mvn clean test -DsuiteXmlFile=src/test/resources/suites/user-suite.xml
```

---

## Run with specific environment

```bash id="pf9hwd"
mvn clean test -Denv=staging
```

---

# CI/CD

## Jenkins

Features:
* scheduled execution
* HTML report publishing
* environment variable support

---

## GitHub Actions

Features:
* cloud-based execution
* automatic execution on push
* scheduled nightly runs
* artifact upload

---

# Security Considerations

## Sensitive Data Protection

Sensitive information is never committed to Git.
Examples:
* tokens
* passwords
* secrets

`.env` is ignored via `.gitignore`.

---

## Log Masking

Sensitive fields are automatically masked in logs.
Example:
```text id="jv2svr"
"password":"****"
```

---

# Future Improvements

Potential future enhancements:
* Docker execution
* Allure reporting
* API schema validation
* Contract testing
* Database validation
* Performance testing
* Kubernetes integration

---

# Author

Vy Nguyen
