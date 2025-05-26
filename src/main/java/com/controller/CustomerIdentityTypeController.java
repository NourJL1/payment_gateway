package com.controller;
import com.model.CUSTOMER_IDENTITY_TYPE;
import com.service.CustomerIdentityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-identity-type")

public class CustomerIdentityTypeController {
	 @Autowired
	    private CustomerIdentityTypeService customerIdentityTypeService;

	    @GetMapping
	    public List<CUSTOMER_IDENTITY_TYPE> getAllCustomerIdentityTypes() {
	        return customerIdentityTypeService.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CUSTOMER_IDENTITY_TYPE> getCustomerIdentityTypeById(@PathVariable Integer id) {
	        Optional<CUSTOMER_IDENTITY_TYPE> customerIdentityType = customerIdentityTypeService.findById(id);
	        return customerIdentityType.map(ResponseEntity::ok)
	                                   .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<CUSTOMER_IDENTITY_TYPE> createCustomerIdentityType(@RequestBody CUSTOMER_IDENTITY_TYPE customerIdentityType) {
	        CUSTOMER_IDENTITY_TYPE createdCustomerIdentityType = customerIdentityTypeService.save(customerIdentityType);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerIdentityType);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CUSTOMER_IDENTITY_TYPE> updateCustomerIdentityType(@PathVariable Integer id, 
	                                                                             @RequestBody CUSTOMER_IDENTITY_TYPE customerIdentityType) {
	        if (!customerIdentityTypeService.findById(id).isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        CUSTOMER_IDENTITY_TYPE updatedCustomerIdentityType = customerIdentityTypeService.save(customerIdentityType);
	        return ResponseEntity.ok(updatedCustomerIdentityType);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomerIdentityType(@PathVariable Integer id) {
	        if (!customerIdentityTypeService.findById(id).isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	        customerIdentityTypeService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    

}
