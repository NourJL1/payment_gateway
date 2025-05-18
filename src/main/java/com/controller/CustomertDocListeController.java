package com.controller;
import com.model.CUSTOMER_DOC_LISTE;
import com.service.CustomerDocListeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-doc-liste")
public class CustomertDocListeController {
	
	@Autowired
    private CustomerDocListeService customerDocListeService;

    @GetMapping
    public List<CUSTOMER_DOC_LISTE> getAllCustomerDocListes() {
        return customerDocListeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER_DOC_LISTE> getCustomerDocListeById(@PathVariable Integer id) {
        Optional<CUSTOMER_DOC_LISTE> customerDocListe = customerDocListeService.findById(id);
        return customerDocListe.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CUSTOMER_DOC_LISTE> createCustomerDocListe(@RequestBody CUSTOMER_DOC_LISTE customerDocListe) {
        CUSTOMER_DOC_LISTE createdCustomerDocListe = customerDocListeService.save(customerDocListe);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDocListe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER_DOC_LISTE> updateCustomerDocListe(@PathVariable Integer id, @RequestBody CUSTOMER_DOC_LISTE customerDocListe) {
        if (!customerDocListeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
       
        CUSTOMER_DOC_LISTE updatedCustomerDocListe = customerDocListeService.save(customerDocListe);
        return ResponseEntity.ok(updatedCustomerDocListe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerDocListe(@PathVariable Integer id) {
        if (!customerDocListeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customerDocListeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
