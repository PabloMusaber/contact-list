package com.contactlist.services.person;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contactlist.entities.Person;
import com.contactlist.exceptions.PersonNotFoundException;
import com.contactlist.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> viewPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person newPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean deletePerson(Long id) {
        if(personRepository.existsById(id)){
            personRepository.deleteById(id);
            return true;
        }
        throw new PersonNotFoundException("There is no person with id number " + id);
    }

    @Override
    public List<Person> findPeopleByName(String name) {
        List<Person> peopleList = personRepository.findByName(name);
        if(!peopleList.isEmpty()){
            return peopleList;
        }else{
            throw new PersonNotFoundException("There is no contact with the name " + name);
        }
    }

    @Override
    public List<Person> findPeopleByCity(String city) {
       List<Person> peopleList = personRepository.findByCity(city);
        if(!peopleList.isEmpty()){
            return peopleList;
        }else{
            throw new PersonNotFoundException("There is no contact in the city " + city);
        }
    }

    @Override
    public List<Person> findPeopleByNameAndCities(String name, List<String> cities) {
        List<Person> peopleList = personRepository.findByNameAndCities(name, cities);
        if(!peopleList.isEmpty()){
            return peopleList;
        }else{
            throw new PersonNotFoundException("There is no contact with the name " + name + " in the " + cities + " cities.");
        }
    }

}

