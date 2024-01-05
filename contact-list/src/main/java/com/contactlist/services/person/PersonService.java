package com.contactlist.services.person;

import java.util.List;

import com.contactlist.entities.Person;

public interface PersonService {

    public List<Person> viewPeople();

    public Person newPerson(Person person);

    public boolean deletePerson(Long id);

    public List<Person> findByName(String name);

    public List<Person> findByCity(String city);

    public List<Person> findByNameAndCity(String name, String city);

}
