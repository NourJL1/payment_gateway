package com.repository;

import com.model.WALLET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WalletRepository extends JpaRepository<WALLET, Integer> {
    List<WALLET> findByCustomerCusCode(Integer cusCode);
    List<WALLET> findByCustomerCusIden(String cusIden);
    List<WALLET> findByCustomerCusMailAddress(String cusMailAddress);
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

    // Updated query to count wallets with wstCode = 1 (ACTIVE)
    @Query("SELECT COUNT(w) FROM WALLET w WHERE w.walletStatus.wstCode = 1")
    Long countActiveWallets();

    @Query("SELECT w FROM WALLET w WHERE " +
            "LOWER(w.walLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "CAST(w.walCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walKey AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walFinId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walEffBal AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walLogicBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walSpecificBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.lastUpdatedDate AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<WALLET> searchWallets(@Param("searchWord") String searchWord);
}