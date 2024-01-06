package com.contactlist.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactlist.entities.Company;
import com.contactlist.services.company.CompanyService;

@RestController
@RequestMapping("/api/companies/")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> newCompany(@RequestBody Company company) {
        Company newCompany = companyService.newCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyService.viewCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @DeleteMapping("{companyId}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long companyId) {
        if (companyService.deleteCompany(companyId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{companyId}/add-contact/{personId}")
    public ResponseEntity<Company> addContact(@PathVariable Long companyId, @PathVariable Long personId) {
        Company updatedCompany = companyService.addContactToCompany(companyId, personId);
        if (updatedCompany != null) {
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
