package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.WALLET_STATUS;
import com.service.WalletStatusService;
@RestController  // FIXED: Added @RestController
@RequestMapping("/api/wallet-status")  // FIXED: Defined base path
public class WalletStatusController {
	@Autowired
    private WalletStatusService service;

    @GetMapping
    public List<WALLET_STATUS> getAllWalletStatuses() {
        return service.getAllWalletStatuses();
    }

    @GetMapping("/{id}")
    public Optional<WALLET_STATUS> getWalletStatusById(@PathVariable Integer id) {
        return service.getWalletStatusById(id);
    }

    @PostMapping
    public WALLET_STATUS createWalletStatus(@RequestBody WALLET_STATUS walletStatus) {
        return service.createWalletStatus(walletStatus);
    }

    @PutMapping("/{id}")
    public WALLET_STATUS updateWalletStatus(@PathVariable Integer id, @RequestBody WALLET_STATUS walletStatusDetails) {
        return service.updateWalletStatus(id, walletStatusDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteWalletStatus(@PathVariable Integer id) {
        service.deleteWalletStatus(id);
    }
      @GetMapping("/search")
    public ResponseEntity<List<WALLET_STATUS>> searchWalletStatuses(@RequestParam("word") String searchWord) {
        List<WALLET_STATUS> walletStatuses = service.searchWalletStatuses(searchWord);
        return ResponseEntity.ok(walletStatuses);
    }
}
