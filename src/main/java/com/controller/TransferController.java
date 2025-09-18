package com.controller;

import com.model.*;
import com.service.AccountService;
import com.service.WalletService;
import com.service.WalletBalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletBalanceHistoryService walletBalanceHistoryService;

    @PostMapping("/account-to-wallet")
    public ResponseEntity<?> transferFromAccountToWallet(@RequestBody TransferRequest request) {
        try {
            // 1. Get account and wallet
            ACCOUNT account = accountService.getAccountById(request.getAccountId());
            if (account == null) {
                return buildErrorResponse("Account not found");
            }

            WALLET wallet = walletService.getWalletById(request.getWalletId())
                    .orElse(null);
            if (wallet == null) {
                return buildErrorResponse("Wallet not found");
            }

            // 2. Validate transfer
            if (request.getAmount() == null || request.getAmount() <= 0) {
                return buildErrorResponse("Amount must be positive");
            }

            if (account.getAccBalance() < request.getAmount()) {
                return buildErrorResponse("Insufficient balance in account");
            }

            // 3. Update account balance (debit)
            account.setAccBalance(account.getAccBalance() - request.getAmount());
            accountService.updateAccount(account.getAccCode(), account);

            // 4. Update wallet balance (credit)
            Float currentEffBal = wallet.getWalEffBal() != null ? wallet.getWalEffBal() : 0f;
            Float currentLogicBal = wallet.getWalLogicBalance() != null ? wallet.getWalLogicBalance() : 0f;

            wallet.setWalEffBal(currentEffBal + request.getAmount().floatValue());
            wallet.setWalLogicBalance(currentLogicBal + request.getAmount().floatValue());

            WALLET updatedWallet = walletService.updateWallet(wallet.getWalCode(), wallet);

            // 5. Create wallet balance history record
            WALLET_BALANCE_HISTORY balanceHistory = new WALLET_BALANCE_HISTORY();
            balanceHistory.setWbhIden(generateBalanceHistoryId());
            balanceHistory.setWbhEffBal(updatedWallet.getWalEffBal());
            balanceHistory.setWbhLogicBalance(updatedWallet.getWalLogicBalance());
            balanceHistory.setWbhSpecificBalance(updatedWallet.getWalSpecificBalance());
            balanceHistory.setWbhLastUpdated(new Date());
            balanceHistory.setWallet(updatedWallet);

            WALLET_BALANCE_HISTORY savedHistory = walletBalanceHistoryService.createWalletBalanceHistory(balanceHistory);

            // 6. Return success response
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Transfer completed successfully");
            response.put("newAccountBalance", account.getAccBalance());
            response.put("newWalletBalance", updatedWallet.getWalEffBal());
            response.put("transactionId", savedHistory.getWbhIden());
            response.put("balanceHistoryId", savedHistory.getWbhCode());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return buildErrorResponse("Transfer failed: " + e.getMessage());
        }
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", message);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private String generateBalanceHistoryId() {
        return "WBH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static class TransferRequest {
        private Integer accountId;
        private Integer walletId;
        private Double amount;

        public Integer getAccountId() { return accountId; }
        public void setAccountId(Integer accountId) { this.accountId = accountId; }

        public Integer getWalletId() { return walletId; }
        public void setWalletId(Integer walletId) { this.walletId = walletId; }

        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
    }
}
