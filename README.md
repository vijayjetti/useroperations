# Spring Boot User Operations API - Streamlined User and Employee Management

This Spring Boot application provides a RESTful API for managing users and employees with comprehensive CRUD operations. It features Swagger documentation, H2 in-memory database integration, and a clean architecture pattern for maintainable enterprise applications.

The application implements a layered architecture with controllers, services, and repositories for both user and employee management. It utilizes Spring Data JPA for database operations and OpenAPI/Swagger for API documentation. The H2 in-memory database ensures quick setup and testing, while the Gradle build system manages dependencies and builds.

## Repository Structure
```
useroperations/
├── src/                              # Source code root directory
│   ├── main/
│   │   ├── java/                     # Java source files
│   │   │   └── com/vijay/learning/useroperations/
│   │   │       ├── config/           # Configuration classes including OpenAPI setup
│   │   │       ├── controller/       # REST controllers for User and Employee endpoints
│   │   │       ├── model/           # Domain model classes
│   │   │       ├── repository/      # Spring Data JPA repositories
│   │   │       └── service/         # Business logic implementation
│   │   └── resources/
│   │       └── application.properties # Application configuration
├── gradle/                           # Gradle wrapper files
└── build.gradle                      # Gradle build configuration
```

## Usage Instructions
### Prerequisites
- Java 8 or higher
- Gradle (included via wrapper)
- IDE with Spring Boot support (recommended)

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
cd useroperations
```

2. Build the project:
```bash
# For Unix-like systems
./gradlew build

# For Windows
gradlew.bat build
```

3. Run the application:
```bash
# For Unix-like systems
./gradlew bootRun

# For Windows
gradlew.bat bootRun
```

### Quick Start

1. Access the Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

2. Create a new user:
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'
```

3. Retrieve all users:
```bash
curl http://localhost:8080/api/v1/users
```

### More Detailed Examples

1. Employee Management:
```bash
# Create an employee
curl -X POST http://localhost:8080/api/v1/employee \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Smith","position":"Manager"}'

# Get employee by ID
curl http://localhost:8080/api/v1/employee/1
```

### Troubleshooting

1. Database Connection Issues
- Error: "Unable to connect to H2 database"
- Solution: 
  - Verify H2 console is enabled in application.properties
  - Access H2 console at http://localhost:8080/h2-console
  - Check connection details in application.properties

2. Application Startup Issues
- Error: "Port 8080 already in use"
- Solution:
  - Kill the process using port 8080
  - Or modify server.port in application.properties

## Data Flow
The application follows a traditional layered architecture for data processing.

```ascii
Client Request → Controller → Service → Repository → H2 Database
     ↑                                                  ↓
     └──────────────── Response ←────────────────────←─┘
```

Component interactions:
1. Controllers receive HTTP requests and validate input
2. Services implement business logic and transaction management
3. Repositories handle data persistence operations
4. H2 Database stores data in-memory
5. OpenAPI/Swagger provides API documentation and testing interface