package com.contactlist.services.person;

import java.util.List;

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
    public List<Person> findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public List<Person> findByCity(String city) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCity'");
    }

    @Override
    public List<Person> findByNameAndCity(String name, String city) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNameAndCity'");
    }
}

