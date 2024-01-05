package com.contactlist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class Person extends Contact{
    @Column(name = "city", nullable = false)
    private String city;

    public Person(Long id, String name, String email, String mobileNumber, String city) {
        super(id, name, email, mobileNumber);
        this.city = city;
    }

    public Person() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}