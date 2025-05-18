package com.controller;

import com.model.FEE_SCHEMA;
import com.service.FeeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fee-schemas")  // Ajout du chemin général
public class FeeSchemaController {
    
    @Autowired
    private FeeSchemaService feeSchemaService;

    @GetMapping
    public List<FEE_SCHEMA> getAllFeeSchemas() {
        return feeSchemaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FEE_SCHEMA> getFeeSchemaById(@PathVariable Integer id) {
        Optional<FEE_SCHEMA> feeSchema = feeSchemaService.findById(id);
        return feeSchema.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FEE_SCHEMA> createFeeSchema(@RequestBody FEE_SCHEMA feeSchema) {
        FEE_SCHEMA createdFeeSchema = feeSchemaService.save(feeSchema);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeeSchema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FEE_SCHEMA> updateFeeSchema(@PathVariable Integer id, @RequestBody FEE_SCHEMA feeSchema) {
        return feeSchemaService.findById(id).map(existingFeeSchema -> {
            existingFeeSchema.setFscIden(feeSchema.getFscIden());
            existingFeeSchema.setFscLabe(feeSchema.getFscLabe());
            existingFeeSchema.setFeeRules(feeSchema.getFeeRules());

            FEE_SCHEMA updatedFeeSchema = feeSchemaService.save(existingFeeSchema);
            return ResponseEntity.ok(updatedFeeSchema);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeeSchema(@PathVariable Integer id) {
        if (!feeSchemaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        feeSchemaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
