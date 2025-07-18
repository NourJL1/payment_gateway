package com.controller;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.service.*;

@RestController
@RequestMapping("/api/customer-identity")
public class CustomerIdentityController {
	
	@Autowired
    private CustomerIdentityService customerIdentityService;
	
	@Autowired
	private CustomerDocListeService customerDocListeService;
	
	 // Get all customer identities
    @GetMapping
    public List<CUSTOMER_IDENTITY> getAllCustomerIdentities() {
        return customerIdentityService.findAll();
    }

    // Get customer identity by ID
    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER_IDENTITY> getCustomerIdentityById(@PathVariable Integer id) {
        Optional<CUSTOMER_IDENTITY> customerIdentity = customerIdentityService.findById(id);
        return customerIdentity.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new customer identity
    /*@PostMapping
    public ResponseEntity<CUSTOMER_IDENTITY> createCustomerIdentity(@Valid @RequestBody CUSTOMER_IDENTITY customerIdentity) {
        CUSTOMER_IDENTITY createdCustomerIdentity = customerIdentityService.save(customerIdentity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerIdentity);
    }*/
    
    @PostMapping
    public ResponseEntity<Object> createCustomerIdentity(@Valid @RequestBody CUSTOMER_IDENTITY customerIdentity) {
        if (customerIdentity.getCustomerDocListe() == null || customerIdentity.getCustomerDocListe().getCdlCode() == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Customer Doc Liste must be provided");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<CUSTOMER_DOC_LISTE> existingDocList = customerDocListeService.findById(customerIdentity.getCustomerDocListe().getCdlCode());

        if (!existingDocList.isPresent()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Customer Doc Liste not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        customerIdentity.setCustomerDocListe(existingDocList.get());
        CUSTOMER_IDENTITY createdCustomerIdentity = customerIdentityService.save(customerIdentity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerIdentity);
    }


    // Update a customer identity
    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER_IDENTITY> updateCustomerIdentity(@PathVariable Integer id, @RequestBody CUSTOMER_IDENTITY customerIdentity) {
        if (!customerIdentityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
      
        CUSTOMER_IDENTITY updatedCustomerIdentity = customerIdentityService.save(customerIdentity);
        return ResponseEntity.ok(updatedCustomerIdentity);
    }

    // Delete a customer identity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerIdentity(@PathVariable Integer id) {
        if (!customerIdentityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customerIdentityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existsByCidNum/{num}")
    public ResponseEntity<Boolean> existsByCidNum(@PathVariable String num) {
        return ResponseEntity.ok().body(customerIdentityService.existsByCidNum(num));
    }
    

}
