package com.danilomassoni.APISimple.services;

import com.danilomassoni.APISimple.exceptions.ResourceNotFoundException;
import com.danilomassoni.APISimple.model.Person;
import com.danilomassoni.APISimple.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices services;

    private Person person0;

    @BeforeEach
    public void setup(){
        person0 = new Person("Danilo", "Guimaraes", "Sao Paulo", "Male", "danilo@gmail.com");
    }

    @DisplayName("Given Person Object when Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
        //Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);
        // When / Act
        Person savedPerson = services.create(person0);

        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Danilo", savedPerson.getFirstName());

    }

    @DisplayName("Given Existing Email when Save Person then Throw Exception")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenThrowException() {
        //Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

        // When / Act
        assertThrows(ResourceNotFoundException.class, () -> {
            services.create(person0);
        });


        //Then / Assert
        verify(repository, never()).save(any(Person.class));

    }

    @DisplayName("Given Persons List when Find All Persons then Return Persons Lists")
    @Test
    void testGivenPersonsList_WhenFindAllPersons_thenReturnPersonsLists() {
        //Given / Arrange

        Person person1 = new Person("Suelen", "Belucio", "Sao Paulo", "Female", "suelen@gmail.com");
        given(repository.findAll()).willReturn(List.of(person0, person1));


        // When / Act
        List<Person> personsList = services.findAll();


        //Then / Assert
        assertNotNull(personsList);
        assertEquals(2, personsList.size());

    }

    @DisplayName("Given Empty Persons List when Find All Persons then Return Empty Persons Lists")
    @Test
    void testGivenEmptyPersonsList_WhenFindAllPersons_thenReturnEmptyPersonsLists() {
        //Given / Arrange

        given(repository.findAll()).willReturn(Collections.emptyList());


        // When / Act
        List<Person> personsList = services.findAll();


        //Then / Assert
        assertTrue(personsList.isEmpty());
        assertEquals(0, personsList.size());

    }

    @DisplayName("Given Person when Find By Id then Return Person Object")
    @Test
    void testGivenPerson_WhenFindById_thenReturnPersonObject() {
        //Given / Arrange


        given(repository.findById(anyLong())).willReturn(Optional.of(person0));


        // When / Act
        Person savedPerson = services.findById(1L);


        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Danilo", savedPerson.getFirstName());

    }

    @DisplayName("Given Person Object when Update Person Id then Return Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnPersonObject() {
        //Given / Arrange

        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setEmail("daniloo@gmail.com");
        person0.setFirstName("Daniloo");

        given(repository.save(person0)).willReturn(person0);

        // When / Act
        Person updatePerson = services.update(person0);


        //Then / Assert
        assertNotNull(updatePerson);
        assertEquals("Daniloo", updatePerson.getFirstName());
        assertEquals("daniloo@gmail.com", updatePerson.getEmail());

    }

    @DisplayName("Given Person ID when Delete Person then Do Nothing")
    @Test
    void testGivenPersonID_WhenDeletePerson_thenDoNothing() {
        //Given / Arrange

        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);


        // When / Act
        services.delete(person0.getId());


        //Then / Assert
        verify(repository, times(1)).delete(person0);

    }










}
