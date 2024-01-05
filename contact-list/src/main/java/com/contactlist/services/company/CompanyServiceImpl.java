package com.contactlist.services.company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.contactlist.entities.Company;
import com.contactlist.entities.Person;
import com.contactlist.repositories.CompanyRepository;
import com.contactlist.repositories.PersonRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

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
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company addContactToCompany(Long companyId, Long personId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalCompany.isPresent() && optionalPerson.isPresent()) {
            Company company = optionalCompany.get();
            List<Person> contacts = company.getContacts();
            Person person = optionalPerson.get();

            // Verificar si la persona ya está en los contactos
            if (!contacts.contains(person)) {
                contacts.add(person);
                company.setContacts(contacts);
                return companyRepository.save(company);
            } else {
                // Excepción
                return null;
            }
        } else {
            // Excepción
            return null;
        }
    }

}