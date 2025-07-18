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
    WALLET searchByCustomerCusCode(Integer cusCode);
    WALLET searchByCustomerCusIden(String cusIden);
    WALLET searchByCustomerCusMailAddress(String cusMailAddress);
    List<WALLET> findByWalIden(String walIden);
    List<WALLET> findByWalLabe(String walLabe);
    List<WALLET> findByWalKey(Integer walKey);
    List<WALLET> findByWalEffBal(Float walEffBal);
    List<WALLET> findByWalLogicBalance(Float walLogicBalance);
    List<WALLET> findByWalSpecificBalance(Float walSpecificBalance);
    List<WALLET> findByWalFinId(Integer walFinId);
    WALLET findByCustomer_CusCode(Integer cusCode);
    List<WALLET> findByWalletStatus_WstCode(Integer wstCode);
    List<WALLET> findByWalletType_WtyCode(Integer wtyCode);
    List<WALLET> findByWalletCategory_WcaCode(Integer wcaCode);
    List<WALLET> findByLastUpdatedDate(LocalDateTime lastUpdatedDate);
    void deleteByWalIden(String walIden);
    List<WALLET> searchWallets(String searchWord);
    Long countActiveWallets();
    Long countPendingWallets();
}