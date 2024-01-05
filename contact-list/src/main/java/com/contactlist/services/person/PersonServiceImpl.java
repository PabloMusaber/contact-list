package com.contactlist.services.person;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.contactlist.entities.Person;
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
        return false;
    }

    @Override
    public List<Person> findPeopleByName(String name) {
        Optional<List<Person>> optionalPeopleList = personRepository.findByName(name);
        if(optionalPeopleList.isPresent()){
            return optionalPeopleList.get();
        }else{
            // Excepcion
            return null;
        }
    }

    @Override
    public List<Person> findPeopleByCity(String city) {
       Optional<List<Person>> optionalPeopleList = personRepository.findByCity(city);
        if(optionalPeopleList.isPresent()){
            return optionalPeopleList.get();
        }else{
            // Excepcion
            return null;
        }
    }

    @Override
    public List<Person> findPeopleByNameAndCities(String name, List<String> cities) {
        Optional<List<Person>> optionalPeopleList = personRepository.findByNameAndCities(name, cities);
        if(optionalPeopleList.isPresent()){
            return optionalPeopleList.get();
        }else{
            // Excepcion
            return null;
        }
    }

}

