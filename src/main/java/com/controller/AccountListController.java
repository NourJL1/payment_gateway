package com.controller;

import com.model.ACCOUNT_LIST;
import com.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-lists")
public class AccountListController {

    @Autowired
    private AccountListService accountListService;

    @PostMapping
    public ResponseEntity<ACCOUNT_LIST> createAccountList(@RequestBody ACCOUNT_LIST accountList) {
        return ResponseEntity.status(201).body(accountListService.create(accountList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ACCOUNT_LIST> updateAccountList(@PathVariable Integer id, @RequestBody ACCOUNT_LIST accountList) {
        ACCOUNT_LIST updated = accountListService.update(id, accountList);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountList(@PathVariable Integer id) {
        accountListService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ACCOUNT_LIST> getAccountListById(@PathVariable Integer id) {
        ACCOUNT_LIST accountList = accountListService.getById(id);
        return accountList != null ? ResponseEntity.ok(accountList) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ACCOUNT_LIST>> getAllAccountLists() {
        return ResponseEntity.ok(accountListService.getAll());
    }
      @GetMapping("/search")
    public ResponseEntity<List<ACCOUNT_LIST>> searchAccountLists(@RequestParam("word") String searchWord) {
        List<ACCOUNT_LIST> accountLists = accountListService.searchAccountLists(searchWord);
        return ResponseEntity.ok(accountLists);
    }
}
