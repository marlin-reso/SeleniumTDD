# 🧪 Selenium TDD Framework — Java

<p align="center">
  <img src="https://media.giphy.com/media/26xBPgjq3xCwqhTLO/giphy.gif" width="600" />
</p>

Welcome to the **Selenium TDD (Test-Driven Development) Framework** built using **Java**, **TestNG**, **Page Object Model (POM)**, **Maven**, and **Jenkins**.

This framework follows **TDD principles**, ensuring tests are written first and automation code evolves based on test cases.

---

## 🚀 Tech Stack

| Tool                   | Purpose                           |
| ---------------------- | --------------------------------- |
| **Selenium WebDriver** | Browser automation                |
| **Java**               | Programming language              |
| **TestNG**             | Testing framework                 |
| **Maven**              | Build + dependency management     |
| **Page Object Model**  | Structured test automation design |
| **Jenkins**            | CI/CD execution                   |

---

## 📚 Framework Features

✔ Follows **TDD workflow** (Red → Green → Refactor)
✔ Page Object Model (POM) design pattern
✔ Centralized WebDriver management
✔ TestNG annotations for test execution control
✔ Maven-managed dependencies
✔ Extent Allure reporting support (optional)
✔ Jenkins pipeline integration
✔ Cross-browser support (Chrome, Firefox, Edge)
✔ Screenshot capture on failure

---

## 🧩 Folder Structure

```
├── allure-results
├── Jenkinsfile
├── pom.xml
├── reports
│   └── TestExecutionReport.html
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── opencart
│   │   │           ├── constants
│   │   │           │   └── AppConstants.java
│   │   │           ├── errors
│   │   │           │   └── AppError.java
│   │   │           ├── exceptions
│   │   │           │   ├── BrowserException.java
│   │   │           │   ├── ElementException.java
│   │   │           │   └── FrameworkException.java
│   │   │           ├── factory
│   │   │           │   ├── DriverFactory.java
│   │   │           │   └── OptionsManager.java
│   │   │           ├── listeners
│   │   │           │   ├── AllureReportListner.java
│   │   │           │   └── ExtentReportListner.java
│   │   │           ├── logger
│   │   │           │   └── Log.java
│   │   │           ├── pages
│   │   │           │   ├── AccountsPage.java
│   │   │           │   ├── EditAccountPage.java
│   │   │           │   ├── LoginPage.java
│   │   │           │   ├── ProductInfoPage.java
│   │   │           │   ├── RegisterationPage.java
│   │   │           │   └── SearchResultsPage.java
│   │   │           ├── utils
│   │   │           │   ├── ElementUtil.java
│   │   │           │   ├── ExcelUtil.java
│   │   │           │   ├── JavaScriptUtil.java
│   │   │           │   ├── StringUtils.java
│   │   │           │   └── TimeUtil.java
│   │   │           └── zpractice
│   │   │               ├── FileReders.java
│   │   │               └── SeleniumWaits.java
│   │   └── resources
│   └── test
│       ├── java
│       │   ├── com
│       │      └── opencart
│       │          ├── base
│       │          │   └── BaseTest.java
│       │          └── tests
│       │              ├── AccountsPageTest.java
│       │              ├── LoginPageTest.java
│       │              ├── ProductPageInfoTest.java
│       │              ├── RegistrationPageTest.java
│       │              └── SearchResultsPageTest.java
│       │   
│       │       
│       └── resources
│           ├── config
│           │   ├── config.dev.properties
│           │   ├── config.properties
│           │   ├── config.qa.properties
│           │   ├── config.stage.properties
│           │   └── config.uat.properties
│           ├── log4j2.xml
│           ├── testdata
│           │   └── opencartTestData.xlsx
│           └── testrunners
│               ├── selenoid_Chrome.xml
│               ├── testng_chrome.xml
│               ├── testng_edge.xml
│               ├── testng_firefox.xml
│               ├── testng_loginPage.xml
│               └── testng_regression.xml




```

---

## 🧪 TDD Workflow Example

### 1️⃣ Write a failing test (RED)

```java
@DataProvider
	public Object[][] getProductSearchData() {

		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" } };

	}

	@Test(dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
```

### 2️⃣ Implement minimum code (GREEN)

```java
@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {

		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

	@Step("Navigate to the register page")
	public RegisterationPage navigateToRegisterPage() {

		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		// It will return the object of the registration page

		return new RegisterationPage(driver);

	}
```

### 3️⃣ Refactor

* Move locators to top
* Add validations
* Add waits

---

## ▶ How to Run Tests

### **Using Maven**

```
mvn clean test
```

### **Using TestNG**

```
testng.xml → Right-click → Run
```

---

## 🔄 CI/CD with Jenkins

* Git webhook triggers pipeline
* Maven builds project
* Selenium tests run on Jenkins agent
* Reports generated and archived

---

## 🌟 Future Enhancements

* Docker execution (Selenium Grid)
* Parallel tests using TestNG
* Integrate Allure/Extent report
* Add Retry logic for flaky tests

---

<p align="center">
  <b>Test first. Automate smart. Deliver quality. 🚀</b>
</p>
