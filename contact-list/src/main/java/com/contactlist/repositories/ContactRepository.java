package com.contactlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactlist.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}