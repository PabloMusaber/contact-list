package com.contactlist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person extends Contact{
    @Column(name = "city", nullable = false)
    private String city;
}
