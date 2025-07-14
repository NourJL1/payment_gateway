package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CUSTOMER_DOC;
import com.service.CustomerDocService;

import lombok.Data;

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

    @GetMapping("file/{id}")
    public ResponseEntity<?> getCustomerDocFileById(@PathVariable Integer id) {
        return customerDocService.findFileById(id);
    }

    @GetMapping("/cdl/{cdlCode}")
    public ResponseEntity<?> getByCustomerDocListe(@PathVariable Integer cdlCode) {
        return customerDocService.findByCustomerDocListe(cdlCode);
    }
    

    @Data
    public class CustomerDocDTO {
        private CUSTOMER_DOC customerDoc;
        private MultipartFile file;    
    }

    @PostMapping 
    public ResponseEntity<CUSTOMER_DOC> createCustomerDoc(@RequestPart("customerDoc") String customerDocJson, @RequestPart("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
        
        CUSTOMER_DOC customerDoc = (new ObjectMapper()).readValue(customerDocJson, CUSTOMER_DOC.class);
        
        CUSTOMER_DOC createdCustomerDoc = customerDocService.save(customerDoc, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDoc);
    }

    /* @PostMapping
    public ResponseEntity<CUSTOMER_DOC> createCustomerDoc(@RequestParam MultipartFile customerDoc) {
        CUSTOMER_DOC createdCustomerDoc = customerDocService.save(customerDoc);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDoc);
    } */

    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER_DOC> updateCustomerDoc(@PathVariable Integer id, @RequestBody CUSTOMER_DOC customerDoc, MultipartFile file) {
        if (!customerDocService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        CUSTOMER_DOC updatedCustomerDoc = customerDocService.save(customerDoc, file);
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
