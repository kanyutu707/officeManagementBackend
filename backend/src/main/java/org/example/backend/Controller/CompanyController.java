package org.example.backend.Controller;

import org.example.backend.Entity.Company;
import org.example.backend.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/authenticate/smartEmployer/company")
public class CompanyController {
    private CompanyService service;

    @Autowired
    public void Service(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Company>> getAll() {
        return service.getAllCompanies();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> getById(@PathVariable int id) {
        return service.getCompanyById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> update(@PathVariable int id, @RequestBody Company company) {
        return service.updateCompany(id, company);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.deleteCompany(id);
    }
}
