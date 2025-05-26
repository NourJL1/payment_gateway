package com.controller;
import com.model.OPERATION_TYPE;
import com.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/operation-types")

public class OperationTypeController {
	@Autowired
    private OperationTypeService operationTypeService;

    // ➤ Récupérer tous les types d'opération
    @GetMapping
    public ResponseEntity<List<OPERATION_TYPE>> getAllOperationTypes() {
        List<OPERATION_TYPE> operationTypes = operationTypeService.getAllOperationTypes();
        return ResponseEntity.ok(operationTypes);
    }

    // ➤ Récupérer un type d'opération par ID
    @GetMapping("/{id}")
    public ResponseEntity<OPERATION_TYPE> getOperationTypeById(@PathVariable Integer id) {
        OPERATION_TYPE operationType = operationTypeService.getOperationTypeById(id);
        return ResponseEntity.ok(operationType);
    }

    // ➤ Créer un nouveau type d'opération
    @PostMapping
    public ResponseEntity<OPERATION_TYPE> createOperationType(@RequestBody OPERATION_TYPE operationType) {
        OPERATION_TYPE createdOperationType = operationTypeService.createOperationType(operationType);
        return ResponseEntity.ok(createdOperationType);
    }

    // ➤ Mettre à jour un type d'opération (SANS modifier le code)
    @PutMapping("/{id}")
    public ResponseEntity<OPERATION_TYPE> updateOperationType(@PathVariable Integer id, 
                                                              @RequestBody OPERATION_TYPE operationType) {
        OPERATION_TYPE updatedOperationType = operationTypeService.updateOperationType(id, operationType);
        return ResponseEntity.ok(updatedOperationType);
    }

    // ➤ Supprimer un type d'opération
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperationType(@PathVariable Integer id) {
        operationTypeService.deleteOperationType(id);
        return ResponseEntity.ok("Operation Type deleted successfully.");
    }
}
