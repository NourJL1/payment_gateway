package com.controller;
import com.model.BANK;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banks")

public class BankController {
	@Autowired
    private BankService bankService;

    @PostMapping
    public ResponseEntity<BANK> create(@RequestBody BANK bank) {
        return ResponseEntity.ok(bankService.create(bank));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BANK> update(@PathVariable Integer id, @RequestBody BANK bank) {
        return ResponseEntity.ok(bankService.update(id, bank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bankService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BANK> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(bankService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BANK>> getAll() {
        return ResponseEntity.ok(bankService.getAll());
    }}
