# Sample Project 1 - Selenium Test Automation

### 📖 Project Description

This is a sample Selenium Test Automation framework designed to automate browser testing for web applications using **Selenium WebDriver** with **Java** and **TestNG**. The framework supports multiple browsers (Chrome, Edge) and utilizes Maven for dependency management.

### Project Structure
```markdown

src/
│
├──── java/                  # Java source files for the framework
│   ├── base/                # Core framework classes like BrowserManager, PageInitializer
│   ├── listeners/           # Listeners like TestLoggerListener for TestNG
│   ├── pages/               # Page Object classes (e.g., LoginPage, DashboardPage)
│   └── tests/               # Test case classes (e.g., LoginTest, CustomerTests)
│   ├── utils/               # Utility classes (e.g., GlobalUtils, PropertiesUtil)
├──── resources/             # Resource files for the framework
│   ├── configs/             # Configuration files (e.g., config.properties)
│   └── testData/            # Test data files (e.g., CSV, JSON, Excel files)
│
├── logs/                    # Logs directory for runtime-generated logs
│   └── screenshots/         # Captured screenshots during test execution
├── pom.xml                  # Maven configuration file for dependencies and build
└── .gitignore               # Specifies files and directories to exclude from version control
```


## 🔥 Important Update (March 2025)

### SeleniumManager Compatibility Issue
If you encounter errors like:
```WARNING: There was an error managing chromedriver... session not created: This version of ChromeDriver only supports Chrome version 132```

This is caused by a mismatch between **ChromeDriver** and your **Chrome browser version**. SeleniumManager may fail to download the correct driver if:

- Your browser updated to a newer version.
- Network issues prevent driver downloads.

---

### ✅ The Fix
Add the following line to your `BrowserManager` class:
```java
System.setProperty("webdriver.http.factory", "jdk-http-client");
```

### 📌 Why This Fix Works

- It forces Selenium to use Java's built-in HTTP client (jdk-http-client) for managing driver downloads.

- Ensures compatibility with SeleniumManager (Selenium 4.6.0 and later).

### 🔧 Troubleshooting Tips

- Clear your driver cache and try again.

- If SeleniumManager fails, you can use WebDriverManager (Bonigarcia) as a fallback.