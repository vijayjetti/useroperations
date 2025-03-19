# Building and Running the User Operations Project

This is a Spring Boot project using Gradle as the build tool. Follow these steps to build and run the application:

1. Make sure you have Java 17 installed on your system
2. Open a terminal in the project root directory

## Building the project
Run one of the following commands:
```bash
# On Windows
.\gradlew.bat clean build

# On Unix-like systems (Linux/MacOS)
./gradlew clean build
```

## Running the project
After building successfully, run one of the following commands:
```bash
# On Windows
.\gradlew.bat bootRun

# On Unix-like systems (Linux/MacOS)
./gradlew bootRun
```

## Accessing the Application
Once the application is running:
- The application will be available at: http://localhost:8080
- H2 Console will be available at: http://localhost:8080/h2-console
- Swagger UI will be available at: http://localhost:8080/swagger-ui.html
- OpenAPI documentation will be available at: http://localhost:8080/api-docs

## Database Configuration
The application uses H2 in-memory database with the following credentials:
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password