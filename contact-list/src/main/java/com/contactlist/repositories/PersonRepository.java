package com.contactlist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactlist.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findByName(String name);

    List<Person> findByCity(String city);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) = LOWER(:name) AND LOWER(p.city) IN :cities")
    List<Person> findByNameAndCities(@Param("name") String name, @Param("cities") List<String> cities);

}
