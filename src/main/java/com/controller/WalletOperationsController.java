package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.model.WALLET_OPERATIONS;
import com.service.WalletOperationsService;

@RestController
@RequestMapping("/api/wallet-operations")

public class WalletOperationsController {
	
	@Autowired
    private WalletOperationsService service;

	    @GetMapping
	    public List<WALLET_OPERATIONS> getAllOperations() {
	        return service.getAllOperations();
	    }

	    @GetMapping("/{WOP_CODE}")
	    public ResponseEntity<WALLET_OPERATIONS> getOperationById(@PathVariable Integer WOP_CODE) {
	        Optional<WALLET_OPERATIONS> operation = service.getOperationById(WOP_CODE);
	        return operation.map(ResponseEntity::ok)
	                        .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public WALLET_OPERATIONS createOperation(@RequestBody WALLET_OPERATIONS operation) {
	        return service.createOperation(operation);
	    }

	    @PutMapping("/{WOP_CODE}")
	    public ResponseEntity<WALLET_OPERATIONS> updateOperation(@PathVariable Integer WOP_CODE, @RequestBody WALLET_OPERATIONS operationDetails) {
	        try {
	        	WALLET_OPERATIONS updatedOperation = service.updateOperation(WOP_CODE, operationDetails);
	            return ResponseEntity.ok(updatedOperation);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{WOP_CODE}")
	    public ResponseEntity<Void> deleteOperation(@PathVariable Integer WOP_CODE) {
	        service.deleteOperation(WOP_CODE);
	        return ResponseEntity.noContent().build();
	    }

}
