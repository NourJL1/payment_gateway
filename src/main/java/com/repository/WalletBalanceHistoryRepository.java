package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WALLET_BALANCE_HISTORY;

public interface WalletBalanceHistoryRepository extends JpaRepository<WALLET_BALANCE_HISTORY, Integer> {
    List<WALLET_BALANCE_HISTORY> findByWallet_WalCode(Integer walCode);

@Query("SELECT wbh FROM WALLET_BALANCE_HISTORY wbh WHERE " +
            "CAST(wbh.wbhCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(wbh.wbhIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(wbh.wbhEffBal AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(wbh.wbhLogicBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(wbh.wbhSpecificBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(wbh.wbhLastUpdated AS string) LIKE CONCAT('%', :searchWord, '%')")
     List<WALLET_BALANCE_HISTORY> searchWalletBalanceHistories(@Param("searchWord") String searchWord);

}
