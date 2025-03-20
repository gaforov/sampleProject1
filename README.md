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

