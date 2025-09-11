package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.model.*;
import com.service.*;

@RestController
@RequestMapping("/api/operation-details")
public class OperationDetailsController {

    @Autowired
    private OperationDetailsService operationDetailsService;

    // Get all operation details
    @GetMapping
    public List<OPERATION_DETAILS> getAllOperationDetails() {
        return operationDetailsService.findAll();
    }

    // Get operation details by ID
    @GetMapping("/{id}")
    public ResponseEntity<OPERATION_DETAILS> getOperationDetailsById(@PathVariable Integer id) {
        Optional<OPERATION_DETAILS> operationDetails = operationDetailsService.findById(id);
        return operationDetails.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new operation details
    @PostMapping
    public ResponseEntity<OPERATION_DETAILS> createOperationDetails(@RequestBody OPERATION_DETAILS operationDetails) {
        OPERATION_DETAILS createdOperationDetails = operationDetailsService.save(operationDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOperationDetails);
    }

    // Update operation details
    @PutMapping("/{id}")
    public ResponseEntity<OPERATION_DETAILS> updateOperationDetails(@PathVariable Integer id,
                                                                    @RequestBody OPERATION_DETAILS operationDetails) {
        Optional<OPERATION_DETAILS> existingOperationDetails = operationDetailsService.findById(id);
        if (!existingOperationDetails.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        operationDetails.setOdeCode(id);
        OPERATION_DETAILS updatedOperationDetails = operationDetailsService.save(operationDetails);
        return ResponseEntity.ok(updatedOperationDetails);
    }

    // Delete operation details by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationDetails(@PathVariable Integer id) {
        if (!operationDetailsService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        operationDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Get transactions by wallet identifier
    @GetMapping("/by-wallet/{walIden}")
    public ResponseEntity<List<OPERATION_DETAILS>> getTransactionsByWallet(@PathVariable String walIden) {
        try {
            List<OPERATION_DETAILS> transactions = operationDetailsService.findByWalletIden(walIden);
            System.out.println("Found " + transactions.size() + " transactions for wallet identifier " + walIden);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            System.out.println("Error in by-wallet: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get recent transactions by customer and wallet
    @GetMapping("/recent-by-customer-wallet")
    public ResponseEntity<List<OPERATION_DETAILS>> getRecentTransactionsByCustomerAndWallet(
            @RequestParam Integer cusCode,
            @RequestParam String walIden,
            @RequestParam(defaultValue = "24") Integer hours) {

        try {
            List<OPERATION_DETAILS> recentTransactions =
                    operationDetailsService.findRecentTransactionsByCustomerAndWallet(cusCode, walIden, hours);
            return ResponseEntity.ok(recentTransactions);
        } catch (Exception e) {
            System.out.println("Error in recent-by-customer-wallet: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Search operation details
    @GetMapping("/search")
    public ResponseEntity<List<OPERATION_DETAILS>> searchOperationDetails(@RequestParam("word") String searchWord) {
        List<OPERATION_DETAILS> operationDetails = operationDetailsService.searchOperationDetails(searchWord);
        return ResponseEntity.ok(operationDetails);
    }

    
}
