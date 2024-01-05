package com.contactlist.services.contact;

import java.util.ArrayList;
import java.util.List;

import com.contactlist.repositories.CompanyRepository;
import com.contactlist.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import com.contactlist.entities.Contact;

@Service
public class ContactServiceImpl implements ContactService {

    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;

    public ContactServiceImpl(CompanyRepository companyRepository, PersonRepository personRepository) {
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Contact> viewContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.addAll(personRepository.findAll());
        contacts.addAll(companyRepository.findAll());
        return contacts;
    }

}
