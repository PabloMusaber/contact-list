package com.contactlist.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends Contact{
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Person> contacts = new ArrayList<Person>();

    public Company() {
    }
    public Company(List<Person> contacts) {
        this.contacts = contacts;
    }

    public Company(Long id, String name, String email, String mobileNumber, List<Person> contacts) {
        super(id, name, email, mobileNumber);
        this.contacts = contacts;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }
}
