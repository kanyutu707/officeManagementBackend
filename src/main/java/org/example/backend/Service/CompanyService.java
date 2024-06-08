package org.example.backend.Service;

import org.example.backend.Entity.Company;
import org.example.backend.Repository.Company_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private Company_Repo repository;

    @Autowired
    public void Repository(Company_Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies=repository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    public ResponseEntity<Company> getCompanyById(int id){
        Optional<Company> companyOptional=repository.findById(id);
        return companyOptional.map(companies->new ResponseEntity<>(companies, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Company> createCompany(Company company){
        Company newCompany=repository.save(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }
    public ResponseEntity<Company> updateCompany(int id, Company updateCompany){
        Optional<Company> companyOptional=repository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate=companyOptional.get();
            companyToUpdate.setName(updateCompany.getName());
            repository.save(companyToUpdate);
            return new ResponseEntity<>(companyToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteCompany(int id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
