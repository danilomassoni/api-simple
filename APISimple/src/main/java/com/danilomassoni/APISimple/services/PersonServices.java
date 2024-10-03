package com.danilomassoni.APISimple.services;

import com.danilomassoni.APISimple.exceptions.ResourceNotFoundException;
import com.danilomassoni.APISimple.model.Person;
import com.danilomassoni.APISimple.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {


    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;


    public List<Person> findAll(){
        logger.info("Finding one people!");


        return repository.findAll();

    }


    public Person findById(Long id) {
        logger.info("Find one person!");


        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create (Person person) {

        logger.info("Creating one person!");

        Optional<Person> savedPerson = repository.findByEmail(person.getEmail());
        if(savedPerson.isPresent()){
            throw new ResourceNotFoundException("Peron already exist with given e-mail " + person.getEmail());
        }

        return repository.save(person);
    }

    public Person update (Person person) {

        logger.info("Update one person!");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setEmail(person.getEmail());

        return repository.save(person);
    }

    public void delete (Long id){
        logger.info("Delete one person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);

    }








}
