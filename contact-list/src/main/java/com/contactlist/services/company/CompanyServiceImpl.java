package com.contactlist.services.company;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.contactlist.entities.Company;
import com.contactlist.entities.Person;
import com.contactlist.exceptions.CompanyNotFoundException;
import com.contactlist.exceptions.DuplicateContactException;
import com.contactlist.exceptions.PersonNotFoundException;
import com.contactlist.repositories.CompanyRepository;
import com.contactlist.repositories.PersonRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, PersonRepository personRepository) {
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Company> viewCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company newCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        log.error("Company not found in deleteCompany method.");
        throw new CompanyNotFoundException("There is no company with id number " + id);
    }

    @Override
    public Company addContactToCompany(Long companyId, Long personId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("The specified company does not exist."));

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("The specified person does not exist."));

        List<Person> contacts = company.getContacts();

        if (contacts.contains(person)) {
            log.error("The person is already a registered contact.");
            throw new DuplicateContactException("The person is already a registered contact.");
        }

        contacts.add(person);
        company.setContacts(contacts);

        try {
            return companyRepository.save(company);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateContactException("The person already belongs to another company.");
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        }
        log.error("Company not found by id.");
        throw new CompanyNotFoundException("There is no company with id number " + id);
    }

}
