# API Automation Testing Framework

## Overview

This project is an enterprise-style API automation testing framework built with:

* Java 17
* Rest Assured
* Maven
* TestNG
* Extent Reports
* Jenkins
* GitHub Actions

Target API:
https://dummyjson.com

The framework follows scalable and maintainable design principles suitable for real-world automation projects.

---

# Features

* REST API automation testing
* Positive & negative test coverage
* Service layer architecture
* POJO-based request/response models
* Reusable assertion layer
* Dynamic test data generation (Faker)
* Request/response logging
* Sensitive data masking
* Extent HTML reporting
* Retry mechanism for flaky tests
* Parallel execution support
* Suite-based execution
* CI/CD integration
* Environment variable support

---

# Tech Stack

| Technology     | Purpose              |
| -------------- | -------------------- |
| Java 17        | Programming language |
| Maven          | Build management     |
| Rest Assured   | API testing          |
| TestNG         | Test framework       |
| Extent Reports | Reporting            |
| Faker          | Test data generation |
| Jenkins        | CI/CD                |
| GitHub Actions | Cloud CI/CD          |

---

# Project Structure

```text
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

# Setup

## Clone repository

```bash
git clone <your-repo-url>
```

---

## Install dependencies

```bash
mvn clean install
```

---

# Environment Variables

Create `.env` file:

```env
BASE_URL=https://dummyjson.com
ENABLE_LOG=true
```

---

# Run Tests

## Run all tests

```bash
mvn clean test
```

---

## Run specific suite

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/user-suite.xml
```

---

# Reports

Extent report generated at:

```text
reports/extent-report.html
```

---

# CI/CD

## Jenkins

* Scheduled execution
* HTML report publishing
* Environment variable support

## GitHub Actions

* Cloud-based execution
* Automatic trigger on push
* Scheduled nightly runs

---

# Security

Sensitive data is masked in logs:

* password
* token
* email

`.env` is ignored via `.gitignore`.

---

# Future Improvements

* Allure reporting
* Docker integration
* Database validation
* Contract testing
* Performance testing
* API schema validation

---

# Author

Vy Nguyen
