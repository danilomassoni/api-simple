# API Simple 

## Overview 

This is a simple API developed using Java Spring Boot, MySQL, JUnit, Mockito and Swagger. It serves as a foundation for handling basic CRUD (Create, Read, Update, Delete) operations, demonstrating the implementation and how test the application using of the best practices for building scalable and maintainable RESTful services. 

## Technologies Used

- Java Spring Boot for developing the RESTful API with clena architecture. 
- MySQL As the relational database. 
- JUnit for testing the API components.
- Mockito for mocking dependencies and write isolate tests. 
- Maven for managing dependencies in project. 

## Lets Start! 

### Prerequisites 

Before start, ensure you have the tools installed: 
- Java 11 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- IDE for developing how IntelliJ or Eclipse

-----------------------------------------------------------------PAREI AQUI --------------------------------------------------------------------------------

### Instalation 

1 - Clone: 

git clone https://github.com/danilomassoni/simple-api.git
cd simple-api

2 Set up MySQL database: 

Create a new database: 

CREATE DATABASE simple_api_db;

Update application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/simple_api_db
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Build the project using Maven:

mvn clean install

Run the Spring Boot application:

mvn spring-boot:run

The API will be running at http://localhost:8080.

API Endpoints
Here is a summary of the available API endpoints:

HTTP Method	Endpoint	Description
GET	/api/items	Retrieve all items
GET	/api/items/{id}	Retrieve item by ID
POST	/api/items	Create a new item
PUT	/api/items/{id}	Update an existing item
DELETE	/api/items/{id}	Delete an item by ID

Example Request (POST)

POST /api/items
Content-Type: application/json

{
  "name": "Sample Item",
  "description": "A sample item for testing",
  "price": 10.99
}


Testing
Unit Tests
Unit tests are written using JUnit and Mockito to ensure each component is tested in isolation. You can run the tests using the following command:

mvn test

The unit tests cover:

Controller methods using mocked services
Service layer tests using mocked repository dependencies
Validation of request objects
Running Tests
To execute all the unit tests:


mvn clean test

Project Structure

simple-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/simpleapi/
│   │   │       ├── controller/     # RestControllers
│   │   │       ├── model/          # Entities
│   │   │       ├── repository/     # Data Repositories
│   │   │       ├── service/        # Business Logic
│   │   │       └── SimpleApiApplication.java # Main Application
│   │   └── resources/
│   │       └── application.properties
│   ├── test/
│   │   └── java/com/example/simpleapi/
│   │       ├── controller/         # Controller Unit Tests
│   │       ├── service/            # Service Unit Tests
├── pom.xml                         # Maven configuration
└── README.md


License
This project is licensed under the MIT License - see the LICENSE file for details.








