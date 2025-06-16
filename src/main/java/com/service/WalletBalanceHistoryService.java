package com.service;
import com.model.WALLET_BALANCE_HISTORY;
import java.util.List;
import java.util.Optional;

public interface WalletBalanceHistoryService {
	 List<WALLET_BALANCE_HISTORY> getAllWalletBalanceHistory();
	    Optional<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryById(Integer wbhCode);
	    List<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryByWallet(Integer walCode);
	    WALLET_BALANCE_HISTORY createWalletBalanceHistory(WALLET_BALANCE_HISTORY walletBalanceHistory);
	    WALLET_BALANCE_HISTORY updateWalletBalanceHistory(Integer wbhCode, WALLET_BALANCE_HISTORY walletBalanceHistoryDetails);
	    void deleteWalletBalanceHistory(Integer wbhCode);
        List<WALLET_BALANCE_HISTORY> searchWalletBalanceHistories(String searchWord);
}
