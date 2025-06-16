package com.service;

import com.model.WALLET;

import java.time.LocalDateTime;
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
      List<WALLET> findByWalIden(String walIden);
    List<WALLET> findByWalLabe(String walLabe);
    List<WALLET> findByWalKey(Integer walKey);
    List<WALLET> findByWalEffBal(Float walEffBal);
    List<WALLET> findByWalLogicBalance(Float walLogicBalance);
    List<WALLET> findByWalSpecificBalance(Float walSpecificBalance);
    List<WALLET> findByWalFinId(Integer walFinId);
    List<WALLET> findByCustomer_CusCode(Integer cusCode);
    List<WALLET> findByWalletStatus_WstCode(Integer wstCode);
    List<WALLET> findByWalletType_WtyCode(Integer wtyCode);
    List<WALLET> findByWalletCategory_WcaCode(Integer wcaCode);
    List<WALLET> findByLastUpdatedDate(LocalDateTime lastUpdatedDate);
    public void deleteByWalIden(String walIden);

    List<WALLET> searchWallets(String searchWord);
}
