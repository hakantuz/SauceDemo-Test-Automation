# 🚀 SauceDemo E2E Test Automation Framework

A robust, scalable, and maintainable End-to-End test automation framework built for the [SauceDemo](https://www.saucedemo.com/) web application. This project demonstrates industry-standard testing practices and architecture.

## 🛠️ Tech Stack & Tools
* **Programming Language:** Java 17
* **Automation Tool:** Selenium WebDriver
* **Test Framework:** TestNG
* **Build Tool:** Maven
* **Design Pattern:** Page Object Model (POM) & Singleton Pattern
* **Reporting:** Extent Reports (v4)

## 🏗️ Architecture Overview
This framework is designed with scalability and reusability in mind:
* **Singleton Driver (`Driver.java`):** Ensures central and thread-safe management of the WebDriver instance. Overcomes modern Chrome CDP version warnings.
* **Page Object Model (`SauceDemoPage.java`):** Web elements and actions are separated from test scripts (`@FindBy`), preventing code duplication and enabling easy maintenance.
* **Base Class (`TestBase.java`):** Centralizes setup (`@BeforeMethod`) and teardown (`@AfterMethod`) operations, including automatic teardown and Extent Report flushing.
* **Configuration Management (`ConfigReader.java`):** Environment variables, URLs, and static test data are managed externally via `configuration.properties`.

## 🧪 Test Coverage
The suite covers both **Happy Path** and **Negative Testing** scenarios:
1. **ShoppingTest:** Validates successful login and adding items to the cart.
2. **NegativeLoginTest:** * Simulates penetration attempts with invalid credentials.
    * Validates proper error handling for locked-out users.

## 📊 Reporting
Integrated with **Extent Reports**, generating dynamic, interactive HTML reports.
Upon execution, reports are automatically saved under the `test-output/` directory with a timestamped format (e.g., `Rapor_20260312...html`), detailing passed/failed steps, skipped tests, and environment info.

## 🚀 How to Run
To execute the entire regression suite, simply run the TestNG XML configuration:
1. Navigate to the root directory.
2. Right-click on `testng.xml`.
3. Select **Run '.../testng.xml'**.

---
*Developed by Hakan - QA Automation Engineer* 🦅