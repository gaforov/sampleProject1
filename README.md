### Project Structure
```markdown


src/
│
├── main/                        # Contains the core framework logic
│   ├── java/                    # Java source files for the framework
│   │   ├── base/                # Core framework classes like BrowserManager, PageInitializer
│   │   ├── listeners/           # Listeners like TestLoggerListener for TestNG
│   │   ├── pages/               # Page Object classes (e.g., LoginPage, DashboardPage)
│   │   ├── utils/               # Utility classes (e.g., GlobalUtils, PropertiesUtil)
│   └── resources/               # Resource files for the framework
│       ├── configs/             # Configuration files (e.g., config.properties)
│       └── testData/            # Test data files (e.g., CSV, JSON, Excel files)
│
├── test/                        # Contains test logic and test-specific files
│   ├── java/                    # Java source files for test cases
│   │   └── tests/               # Test case classes (e.g., LoginTest, CustomerTests)
│   └── resources/               # Test-specific resources, if needed (optional)
│
├── logs/                        # Logs directory for runtime-generated logs
├── pom.xml                      # Maven configuration file for dependencies and build
└── .gitignore                   # Specifies files and directories to exclude from version control
```

