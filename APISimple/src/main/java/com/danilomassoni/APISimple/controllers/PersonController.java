package com.danilomassoni.APISimple.controllers;


import com.danilomassoni.APISimple.model.Person;
import com.danilomassoni.APISimple.services.PersonServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable(value = "id") Long id) throws Exception{

        try{
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {

        return service.findAll();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) throws Exception{

        return service.create(person);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person) {

        try{
            return ResponseEntity.ok(service.update(person));
            }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{

        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
