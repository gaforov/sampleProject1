# Sample Project 1 - Selenium Test Automation

### 📖 Project Description

This is a sample Selenium Test Automation framework designed to automate browser testing for web applications using **Selenium WebDriver** with **Java** and **TestNG**. The framework supports multiple browsers (Chrome, Edge) and utilizes Maven for dependency management.

### Project Structure
```markdown

src/
├──── java/                           # Java source files for the framework (Test sources root)
│       ├── base/                     # Core framework classes like BrowserManager, PageInitializer
│       │    ├── BrowserManager       # Manages WebDriver lifecycle using ThreadLocal
│       │    └── PageInitializer      # Centralized page object instantiation
│       ├── listeners/                # TestNG listeners for reporting and logging
│       │    └── TestLoggerListener   # Custom TestNG listener for logging
│       ├── pages/                    # Page Object classes
│       │    ├── LoginPage            # Page object for login functionality
│       │    └── SuccessLoginPage     # Page object for successful login
│       ├── tests/                    # Test case classes
│       │    ├── LoginTest            # Test cases for login functionality
│       │    └── SuccessLoginTest     # Test cases for successful login
│       └── utils/                    
│            ├── BrowserUtils         # Browser-related utility methods (e.g., launch profile, switch tabs, etc.)
│            ├── ElementUtils         # Common element interactions (click, type, etc.)
├──── resources/                      # Resource files for the framework (Test resources root)
│     ├── configs/                    # Configuration files (e.g., config.properties)
│     │       └── config.properties   # Centralized test configuration file
│     └── testData/                   # Test data files (e.g., CSV, JSON, Excel files)
│
├── logs/                   # Logs directory for runtime-generated logs
│   └── screenshots/        # Captured screenshots during test execution
├── pom.xml                 # Maven configuration file for dependencies and build
└── .gitignore              # Specifies files and directories to exclude from version control
```

## 🔥  Update (May 2025)
### Reconstructed Project Structure
The project structure has been reconstructed to improve organization and maintainability. The new structure separates test cases, page objects, and utility classes into distinct packages. This allows for better scalability and easier navigation within the project.
### Removed SeleniumManager Compatibility Issue
As of May 2025, the compatibility issue with SeleniumManager has been resolved. The framework now uses the latest version of Selenium WebDriver, which includes built-in support for managing browser drivers. This eliminates the need for manual driver management and ensures seamless integration with the latest browser versions.
### Updated project structure tree above
The new updated project structure reflects the changes made to the framework. The `src` directory now contains separate packages for test cases, page objects, and utility classes. This organization enhances code readability and maintainability, making it easier for developers to navigate and understand the project.

## 🔥  Update (March 2025)

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