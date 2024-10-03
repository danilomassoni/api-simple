package com.danilomassoni.APISimple;

import com.danilomassoni.APISimple.integrationtests.testcontainers.AbstractIntegrationTest;
import com.danilomassoni.APISimple.model.Person;
import com.danilomassoni.APISimple.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTestTest extends AbstractIntegrationTest {

    @Autowired
    private PersonRepository repository;

    private Person person0;

    @BeforeEach
    public void setup(){
        //Given / Arrange
        person0 = new Person("Danilo", "Guimaraes", "Sao Paulo", "Male", "danilo@gmail.com");
    }

    @Test
    @DisplayName("Given Person Object when Save then Return Saved Person")
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {
        //Given / Arrange


        //When / Act
        Person savedPerson = repository.save(person0);
        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @Test
    @DisplayName("Given Person List when Find All then Return Person List")
    void testGivenPersonList_whenFindAll_thenReturnPersonList() {
        //Given / Arrange

        Person person1 = new Person("Suelen", "Belucio", "Sao Paulo", "Female", "suelen@gmail.com");

        repository.save(person0);
        repository.save(person1);

        //When /Act
        List<Person> personList = repository.findAll();

        //Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @Test
    @DisplayName("Given Person Id When Find By Id then Return Person Id")
    void testGivenPersonId_whenFindById_thenReturnPersonId() {
        //Given /Arrange

        repository.save(person0);
        //When / Act
        Person savedPerson = repository.findById(person0.getId()).get();

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());

    }

    @Test
    @DisplayName("Given Person Email when Find By Email then Return Person Object")
    void testGivenPersonEmail_whenFindByEmail_thenReturnPersonObject() {
        //Given / Arrange

        repository.save(person0);

        //When /Act
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();

        //Then /Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getEmail(), savedPerson.getEmail());
    }

    @Test
    @DisplayName("Given Person Object when Update Person then Return Update Person Object")
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject() {
        //Given / Arrange

        repository.save(person0);

        //When /Act
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Dan");
        savedPerson.setEmail("dan@gmail.com");

        Person updatedPerson = repository.save(savedPerson);

        // Then / Assert
        assertNotNull(updatedPerson);
        assertEquals("Dan", updatedPerson.getFirstName());
        assertEquals("dan@gmail.com", updatedPerson.getEmail());
    }

    @Test
    @DisplayName("Given Person Object when Delete Person then Remove Person")
    void testGivenPersonObject_whenDeletePerson_thenRemovePerson(){
        //Given / Arrange

        repository.save(person0);


        //When / Act
        repository.deleteById(person0.getId());
        Optional<Person> personOptional = repository.findById(person0.getId());

        //Then / Assert
        assertTrue(personOptional.isEmpty());

    }

    @Test
    @DisplayName("Given FirstName and LastName when FindJPQL then Return Person Object")
    void testGivenFirstNAmeAndLastName_whenFindJPQL_thenReturnPersonObject(){
        //Given / Arrange

        repository.save(person0);

        String firstName = "Danilo";
        String lastName = "Guimaraes";

        //When /Act
        Person savedPerson = repository.findByJPQL(firstName, lastName);

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("Given FirstName and LastName when FindJPQLNamed then Return Person Object")
    void testGivenFirstNAmeAndLastName_whenFindJPQLNamed_thenReturnPersonObject(){
        //Given / Arrange

        repository.save(person0);

        String firstName = "Danilo";
        String lastName = "Guimaraes";

        //When /Act
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("Given FirstName and LastName when FindJPQLNativeSQL then Return Person Object")
    void testGivenFirstNAmeAndLastName_whenFindJPQLNativeSQL_thenReturnPersonObject(){
        //Given / Arrange

        repository.save(person0);

        String firstName = "Danilo";
        String lastName = "Guimaraes";

        //When /Act
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

    @Test
    @DisplayName("Given FirstName and LastName when FindJPQLNativeSQL with Named Parameters then Return Person Object")
    void testGivenFirstNAmeAndLastName_whenFindJPQLNativeSQLwithNamedParameters_thenReturnPersonObject(){
        //Given / Arrange

        repository.save(person0);

        String firstName = "Danilo";
        String lastName = "Guimaraes";

        //When /Act
        Person savedPerson = repository.findByNativeSQLwithNamedParameters(firstName, lastName);

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());

    }

}