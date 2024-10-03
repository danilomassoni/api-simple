package com.danilomassoni.APISimple.integrationtests.controller;


import com.danilomassoni.APISimple.config.TestConfigs;
import com.danilomassoni.APISimple.integrationtests.testcontainers.AbstractIntegrationTest;
import com.danilomassoni.APISimple.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person("Danilo", "Guimaraes", "Sao Paulo", "Male", "danilo@gmail.com");
    }

    @DisplayName("Integration Given Person Object when Create One Person Should Return A Person Object")
    @Order(1)
    @Test
    void integrationTestGivenPersonObject_whenCreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Danilo", createdPerson.getFirstName());
        assertEquals("Guimaraes", createdPerson.getLastName());
        assertEquals("Sao Paulo", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("danilo@gmail.com", createdPerson.getEmail());
    }

    @DisplayName("Integration Given Person Object when Update One Person Should Return A Update Person Object")
    @Order(2)
    @Test
    void integrationTestGivenPersonObject_whenUpdateOnePerson_ShouldReturnAUpdatePersonObject() throws JsonProcessingException {

        person.setFirstName("Dan");
        person.setEmail("dan@gmail.com");

        var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                    .put()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Dan", createdPerson.getFirstName());
        assertEquals("Guimaraes", createdPerson.getLastName());
        assertEquals("Sao Paulo", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("dan@gmail.com", createdPerson.getEmail());
    }

    @DisplayName("Integration Given Person Object when Find By Id One Person Should Return A Person Object")
    @Order(3)
    @Test
    void integrationTestGivenPersonObject_whenFindById_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given().spec(specification)
                    .pathParam("id", person.getId())
                .when()
                    .get("/{id}")
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person foundPerson = objectMapper.readValue(content, Person.class);


        assertNotNull(foundPerson);
        assertNotNull(foundPerson.getId());
        assertNotNull(foundPerson.getFirstName());
        assertNotNull(foundPerson.getLastName());
        assertNotNull(foundPerson.getAddress());
        assertNotNull(foundPerson.getGender());
        assertNotNull(foundPerson.getEmail());

        assertTrue(foundPerson.getId() > 0);
        assertEquals("Dan", foundPerson.getFirstName());
        assertEquals("Guimaraes", foundPerson.getLastName());
        assertEquals("Sao Paulo", foundPerson.getAddress());
        assertEquals("Male", foundPerson.getGender());
        assertEquals("dan@gmail.com", foundPerson.getEmail());
    }

    @DisplayName("Integration Given when Find All Should Return A Person List")
    @Order(4)
    @Test
    void integrationTest_whenFindAll_ShouldReturnAPersonList() throws JsonProcessingException {

        Person anotherPerson = new Person("Suelen", "Belucio", "Sao Paulo", "Female", "suelen@gmail.com" );

        given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(anotherPerson)
                .when()
                    .post()
                .then()
                    .statusCode(200);

        var content = given().spec(specification)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        Person[] myArray = objectMapper.readValue(content, Person[].class);
        List<Person> people = Arrays.asList(myArray);

        Person foundPersonOne = people.get(0);

        assertNotNull(foundPersonOne);
        assertNotNull(foundPersonOne.getId());
        assertNotNull(foundPersonOne.getFirstName());
        assertNotNull(foundPersonOne.getLastName());
        assertNotNull(foundPersonOne.getAddress());
        assertNotNull(foundPersonOne.getGender());
        assertNotNull(foundPersonOne.getEmail());

        assertTrue(foundPersonOne.getId() > 0);
        assertEquals("Dan", foundPersonOne.getFirstName());
        assertEquals("Guimaraes", foundPersonOne.getLastName());
        assertEquals("Sao Paulo", foundPersonOne.getAddress());
        assertEquals("Male", foundPersonOne.getGender());
        assertEquals("dan@gmail.com", foundPersonOne.getEmail());

        Person foundPersonTwo = people.get(1);

        assertNotNull(foundPersonTwo);
        assertNotNull(foundPersonTwo.getId());
        assertNotNull(foundPersonTwo.getFirstName());
        assertNotNull(foundPersonTwo.getLastName());
        assertNotNull(foundPersonTwo.getAddress());
        assertNotNull(foundPersonTwo.getGender());
        assertNotNull(foundPersonTwo.getEmail());

        assertTrue(foundPersonTwo.getId() > 0);
        assertEquals("Suelen", foundPersonTwo.getFirstName());
        assertEquals("Belucio", foundPersonTwo.getLastName());
        assertEquals("Sao Paulo", foundPersonTwo.getAddress());
        assertEquals("Female", foundPersonTwo.getGender());
        assertEquals("suelen@gmail.com", foundPersonTwo.getEmail());
    }

    @DisplayName("Integration Given Person Object when Delete Should Return Not Content")
    @Order(5)
    @Test
    void integrationTestGivenPersonObject_whenDelete_ShouldReturnNoContent() throws JsonProcessingException {

        given().spec(specification)
                    .pathParam("id", person.getId())
                .when()
                    .delete("/{id}")
                .then()
                    .statusCode(204);



    }






}
