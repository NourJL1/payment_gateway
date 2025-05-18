package com.controller;
import com.model.WALLET_BALANCE_HISTORY;
import com.service.WalletBalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet-balance-history")

public class WalletBalanceHistoryController {
	@Autowired
    private WalletBalanceHistoryService service;

    @GetMapping
    public List<WALLET_BALANCE_HISTORY> getAllWalletBalanceHistories() {
        return service.getAllWalletBalanceHistory();
    }

    @GetMapping("/{id}")
    public Optional<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryById(@PathVariable Integer id) {
        return service.getWalletBalanceHistoryById(id);
    }

    @GetMapping("/wallet/{walCode}")
    public List<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryByWallet(@PathVariable Integer walCode) {
        return service.getWalletBalanceHistoryByWallet(walCode);
    }

    @PostMapping
    public WALLET_BALANCE_HISTORY createWalletBalanceHistory(@RequestBody WALLET_BALANCE_HISTORY walletBalanceHistory) {
        return service.createWalletBalanceHistory(walletBalanceHistory);
    }

    @PutMapping("/{id}")
    public WALLET_BALANCE_HISTORY updateWalletBalanceHistory(@PathVariable Integer id, @RequestBody WALLET_BALANCE_HISTORY walletBalanceHistoryDetails) {
        return service.updateWalletBalanceHistory(id, walletBalanceHistoryDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteWalletBalanceHistory(@PathVariable Integer id) {
        service.deleteWalletBalanceHistory(id);
    }}
