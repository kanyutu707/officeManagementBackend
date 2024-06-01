package org.example.backend.Service;

import org.example.backend.Entity.Financials;
import org.example.backend.Repository.Financial_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialService {
    private Financial_Repo repository;

    @Autowired
    public void Repository(Financial_Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity <List<Financials>> getAllFinancials() {
        List<Financials> financials = repository.findAll();
        return new ResponseEntity<>(financials, HttpStatus.OK);
    }

    public ResponseEntity<Financials> getFinancialById(int id) {
        Optional<Financials> financialsOptional=repository.findById(id);
        return financialsOptional.map(financials -> new ResponseEntity<>(financials, HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Financials> createFinancial(Financials financial){
        Financials newFinancial=repository.save(financial);
        return new ResponseEntity<>(newFinancial,HttpStatus.CREATED);
    }

    public ResponseEntity<Financials> updateFinancial(int id, Financials updateFinancial){
        Optional<Financials> financialOptional=repository.findById(id);
        if(financialOptional.isPresent()){
            Financials financialToUpdate=financialOptional.get();
            financialToUpdate.setSource(updateFinancial.getSource());
            financialToUpdate.setDescription(updateFinancial.getDescription());
            financialToUpdate.setAmount(updateFinancial.getAmount());
            repository.save(financialToUpdate);
            return new ResponseEntity<>(financialToUpdate,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteFinancial(int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
