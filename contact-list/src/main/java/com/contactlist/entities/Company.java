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
}
