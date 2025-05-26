package com.controller;
import com.model.ACCOUNT_TYPE;


import com.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account-types")
@CrossOrigin(origins = "*")  //eviter les erreurs cors
public class AccountTypeController {
	@Autowired
    private AccountTypeService accountTypeService;

    @PostMapping
    public ResponseEntity<ACCOUNT_TYPE> create(@RequestBody ACCOUNT_TYPE accountType) {
        return ResponseEntity.ok(accountTypeService.create(accountType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ACCOUNT_TYPE> update(@PathVariable Integer id, @RequestBody ACCOUNT_TYPE accountType) {
        return ResponseEntity.ok(accountTypeService.update(id, accountType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ACCOUNT_TYPE> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(accountTypeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ACCOUNT_TYPE>> getAll() {
        return ResponseEntity.ok(accountTypeService.getAll());
     
    }
}
