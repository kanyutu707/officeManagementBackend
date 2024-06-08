package org.example.backend.Controller;
import org.example.backend.Entity.Financials;
import org.example.backend.Service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/smartEmployer/financials")
public class FinancialController {
    private FinancialService service;

    @Autowired
    public void Service(FinancialService service) {
        this.service = service;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Financials>> getAllFinancials(){
        return service.getAllFinancials();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Financials> getEventById(@PathVariable int id) {
        return service.getFinancialById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<Financials> createEvent(@RequestBody Financials financial) {
        return service.createFinancial(financial);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Financials> updateEvent(@PathVariable int id, @RequestBody Financials financial) {
        return service.updateFinancial(id, financial);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        service.deleteFinancial(id);
        return ResponseEntity.noContent().build();
    }


}
