package com.controller;
import com.model.FEES;
import com.service.FeesService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeesController {
    @Autowired
    private FeesService feesService;

    @GetMapping
    public List<FEES> getAllFees() {
        return feesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FEES> getFeesById(@PathVariable Integer id) {
        try {
            FEES fees = feesService.getById(id);
            return ResponseEntity.ok(fees);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FEES> createFees(@RequestBody FEES fees) {
        FEES createdFees = feesService.create(fees);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FEES> updateFees(@PathVariable Integer id, @RequestBody FEES fees) {
        try {
            FEES updatedFees = feesService.update(id, fees);
            return ResponseEntity.ok(updatedFees);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFees(@PathVariable Integer id) {
        try {
            feesService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<FEES>> searchFees(@RequestParam("word") String searchWord) {
        List<FEES> fees = feesService.searchFees(searchWord);
        return ResponseEntity.ok(fees);
    }
}
