package com.controller;

import com.model.CUSTOMER_DOC;
import com.service.CustomerDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-doc")
public class CustomerDocController {
	
	@Autowired
    private CustomerDocService customerDocService;

    @GetMapping
    public List<CUSTOMER_DOC> getAllCustomerDocs() {
        return customerDocService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER_DOC> getCustomerDocById(@PathVariable Integer id) {
        Optional<CUSTOMER_DOC> customerDoc = customerDocService.findById(id);
        return customerDoc.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CUSTOMER_DOC> createCustomerDoc(@RequestBody CUSTOMER_DOC customerDoc) {
        CUSTOMER_DOC createdCustomerDoc = customerDocService.save(customerDoc);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDoc);
    }

    /* @PostMapping
    public ResponseEntity<CUSTOMER_DOC> createCustomerDoc(@RequestParam MultipartFile customerDoc) {
        CUSTOMER_DOC createdCustomerDoc = customerDocService.save(customerDoc);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDoc);
    } */

    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER_DOC> updateCustomerDoc(@PathVariable Integer id, @RequestBody CUSTOMER_DOC customerDoc) {
        if (!customerDocService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        CUSTOMER_DOC updatedCustomerDoc = customerDocService.save(customerDoc);
        return ResponseEntity.ok(updatedCustomerDoc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerDoc(@PathVariable Integer id) {
        if (!customerDocService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customerDocService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CUSTOMER_DOC>> searchCustomerDocs(@RequestParam("word") String searchWord) {
        List<CUSTOMER_DOC> customerDocs = customerDocService.searchCustomerDocs(searchWord);
        return ResponseEntity.ok(customerDocs);
    }

}
