package com.controller;

import com.model.CUSTOMER_STATUS;
import com.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-status")
public class CustomerStatusController {
    
    @Autowired
    private CustomerStatusService customerStatusService;

    @GetMapping
    public ResponseEntity<List<CUSTOMER_STATUS>> getAllCustomerStatuses() {
        return ResponseEntity.ok().body(customerStatusService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER_STATUS> getCustomerStatusById(@PathVariable Integer id) {
        return customerStatusService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CUSTOMER_STATUS> createCustomerStatus(@RequestBody CUSTOMER_STATUS customerStatus) {
        CUSTOMER_STATUS createdCustomerStatus = customerStatusService.save(customerStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER_STATUS> updateCustomerStatus(@PathVariable Integer id, 
                                                                @RequestBody CUSTOMER_STATUS customerStatus) {
        Optional<CUSTOMER_STATUS> existingStatus = customerStatusService.findById(id);
        if (!existingStatus.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        CUSTOMER_STATUS updatedStatus = existingStatus.get();
        updatedStatus.setCtsIden(customerStatus.getCtsIden());
        updatedStatus.setCtsLabe(customerStatus.getCtsLabe());

        return ResponseEntity.ok(customerStatusService.save(updatedStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerStatus(@PathVariable Integer id) {
        if (!customerStatusService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customerStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CUSTOMER_STATUS>> searchCustomerStatuses(@RequestParam("word") String searchWord) {
        List<CUSTOMER_STATUS> customerStatuses = customerStatusService.searchCustomerStatuses(searchWord);
        return ResponseEntity.ok(customerStatuses);
    }
}
