package com.contactlist.services.company;

import java.util.List;

import com.contactlist.entities.Company;

public interface CompanyService {

    public List<Company> viewCompanies();

    public Company newCompany(Company company);

    public boolean deleteCompany(Long id);

    public Company addContactToCompany(Long companyId, Long personId);

}
