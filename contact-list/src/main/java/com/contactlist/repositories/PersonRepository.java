package com.contactlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactlist.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
