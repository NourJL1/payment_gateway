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

	    @PutMapping("/{id}")
	    public ResponseEntity<OPERATION_DETAILS> updateOperationDetails(@PathVariable Integer id,
	                                                                   @RequestBody OPERATION_DETAILS operationDetails) {
	        Optional<OPERATION_DETAILS> existingOperationDetails = operationDetailsService.findById(id);
	        if (!existingOperationDetails.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }

	        // Injecter l'ID dans l'objet reçu
	        operationDetails.setOdeCode(id);

	        OPERATION_DETAILS updatedOperationDetails = operationDetailsService.save(operationDetails);
	        return ResponseEntity.ok(updatedOperationDetails);
	    }


	    // Delete operation details by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteOperationDetails(@PathVariable Integer id) {
	        // Check if the record exists
	        if (!operationDetailsService.findById(id).isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	        operationDetailsService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

}
