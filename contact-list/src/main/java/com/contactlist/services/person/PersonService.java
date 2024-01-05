package com.contactlist.services.person;

import java.util.List;

import com.contactlist.entities.Person;

public interface PersonService {

    public List<Person> viewPeople();

    public Person newPerson(Person person);

    public boolean deletePerson(Long id);

    public List<Person> findPeopleByName(String name);

    public List<Person> findPeopleByCity(String city);

    public List<Person> findPeopleByNameAndCities(String name, List<String> cities);

}
