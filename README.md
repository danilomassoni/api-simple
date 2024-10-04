# API Simple 

## Overview 

This is a simple API developed using Java Spring Boot, MySQL, JUnit, Mockito, Docker and Swagger. It serves as a foundation for handling basic CRUD (Create, Read, Update, Delete) operations, demonstrating the implementation and how test the application using of the best practices for building scalable and maintainable RESTful services. 

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


### Instalation 

1 - Clone: 

git clone https://github.com/danilomassoni/simple-api.git
cd simple-api

2 Set up MySQL database: 

Create a new database: 

CREATE DATABASE rest_with_spring_boot_apisimple;

Update application.yml with your MySQL credentials:

spring:
  datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/rest_with_spring_boot_apisimple?useTimezone=true&serverTimezone=UTC
      username: root
      password: root
  jpa:
      hibernate:
        ddl-auto: update
      properties:
        hibernate:
          dialect: org.hibernate.dialect.MySQL8Dialect
      show-sql: false

Build the project using Maven:

mvn clean install

Run the Spring Boot application:

mvn spring-boot:run

The API will be running at http://localhost:8080.

API Endpoints
Here of the available API endpoints:

HTTP Method	Endpoint	Description
GET	/person	Retrieve all person
GET	/person/{id}	Retrieve item by ID
POST	/person	Create a new item
PUT	/items/{id}	Update an existing item
DELETE	/items/{id}	Delete an item by ID

Example Request (POST)

POST /api/person
Content-Type: application/json

{
  "firstName": "Danilo",
  "lastName": "Massoni",
  "address": "Sao Paulo",
  "gender": "Male",
  "email": "danilo@gmail.com"
}


Testing
Unit Tests
Unit tests are written using JUnit and Mockito to ensure each component is tested in isolation. The application for test: 

server:
  port: 8888
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
    show-sql: false


The unit tests cover:

Controller methods using mocked services
Service layer tests using mocked repository dependencies
Validation of request objects
Running Tests
To execute all the unit tests:

mvn clean test

Project Structure

APISimple/
|-.idea
|-.mvn
|-src
     |-main
           |-java
                 |-.com.danilomassoni.APISimple
                                              |-config / #OpenAPIConfig
                                              |-controllers / #PersonController
                                              |-exceptions / #ExceptionResponse #ResourceNotFoundException
                                                          |-handler / #CostomizedResponseEntityException
                                              |-model / #Person
                                              |-respositores / #PersonRepository
                                              |-services / #PersonServices
                                              #ApiSimpleApplication
           |-resources / #application.yml     
    |-test 
          |-java     
                |-com.danilomassoni.APISimple
                                             |-config / #TestConfigs
                                             |-controllers / #PersonControllersTest
                                             |-integationtest 
                                                             |-controller / #PersonControllerIntegrationTest
                                                             |-swagger / SwaggerIntegrationTest
                                                             |-testcontainers / #AbstractIntegrationTest
                                             |-services / #PersonServicesTest
                                             |-PersonRepositoryTest
                |-resources / #application.yml   
|-pom.xml
|READM.MD                













