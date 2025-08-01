package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.WalletTransferService;

import dto.WalletToWalletTransferRequest;
import dto.WalletToWalletTransferResponse;

@RestController
@RequestMapping("/api/transfer") // <- groupement des routes liées au transfert
public class WalletTransferController {
    @Autowired
    private WalletTransferService walletTransferService;

    @PostMapping("/wallet-to-wallet")
    public WalletToWalletTransferResponse transfer(@RequestBody WalletToWalletTransferRequest request) {
        return walletTransferService.transfer(request);
    }
}
