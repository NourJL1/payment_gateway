package com.service;

import com.model.WALLET;
import java.util.List;
import java.util.Optional;

public interface WalletService {
    WALLET createWallet(WALLET wallet);
    Optional<WALLET> getWalletById(Integer walCode);
    List<WALLET> getAllWallets();
    WALLET updateWallet(Integer walCode, WALLET wallet);
    void deleteWallet(Integer walCode);
   // List<WALLET> getWalletsByCustomerCode(String customerCode);
    //List<WALLET> getWalletsByStatus(String status);
    
    List<WALLET> searchByCustomerCusCode(Integer cusCode);
    List<WALLET> searchByCustomerCusIden(String cusIden);
    List<WALLET> searchByCustomerCusMailAddress(String cusMailAddress);
}
