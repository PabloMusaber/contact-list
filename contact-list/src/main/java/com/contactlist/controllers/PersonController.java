package com.contactlist.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.contactlist.services.person.PersonService;

import com.contactlist.entities.Person;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> newPerson(@RequestBody Person person){
        Person newPerson = personService.newPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<Person>> getPeople(){
        List<Person> contacts = personService.viewPeople();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long personId){
        if(personService.deletePerson(personId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity <List<Person>> getPeopleByName(@PathVariable String name){
        List<Person> people = personService.findPeopleByName(name);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/find-by-city/{city}")
    public ResponseEntity <List<Person>> getPeopleByCity(@PathVariable String city){
        List<Person> people = personService.findPeopleByCity(city);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/find-by-name-and-cities/{name}")
    public ResponseEntity<List<Person>> findPeopleByNameAndCities(@PathVariable String name,
        @RequestParam List<String> cities) {
        List<Person> people = personService.findPeopleByNameAndCities(name, cities);
            return new ResponseEntity<>(people, HttpStatus.OK);
    }

}
