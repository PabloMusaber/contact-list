package com.contactlist.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactlist.entities.Contact;
import com.contactlist.services.contact.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity <List<Contact>> getContacts(){
        List<Contact> contacts = contactService.viewContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

}
