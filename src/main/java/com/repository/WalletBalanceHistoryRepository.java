package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.WALLET_BALANCE_HISTORY;

public interface WalletBalanceHistoryRepository extends JpaRepository<WALLET_BALANCE_HISTORY, Integer> {
    List<WALLET_BALANCE_HISTORY> findByWallet_WalCode(Integer walCode);

}
