package com.servicesImp;
import com.model.WALLET_BALANCE_HISTORY;
import com.repository.WalletBalanceHistoryRepository;
import com.service.WalletBalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@Service
public class WalletBalanceHistoryServiceImp implements WalletBalanceHistoryService {

    @Autowired
    private WalletBalanceHistoryRepository repository;

	@Override
	public List<WALLET_BALANCE_HISTORY> getAllWalletBalanceHistory() {
        return repository.findAll();

	}

	@Override
	public Optional<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryById(Integer wbhCode) {
        return repository.findById(wbhCode);

	}

	@Override
	public List<WALLET_BALANCE_HISTORY> getWalletBalanceHistoryByWallet(Integer walCode) {
        return repository.findByWallet_WalCode(walCode);

	}

	@Override
	public WALLET_BALANCE_HISTORY createWalletBalanceHistory(WALLET_BALANCE_HISTORY walletBalanceHistory) {
        return repository.save(walletBalanceHistory);
	}

	@Override
	public WALLET_BALANCE_HISTORY updateWalletBalanceHistory(Integer wbhCode,
	        WALLET_BALANCE_HISTORY walletBalanceHistoryDetails) {
	    return repository.findById(wbhCode).map(walletBalanceHistory -> {
	        walletBalanceHistory.setWbhEffBal(walletBalanceHistoryDetails.getWbhEffBal());
	        walletBalanceHistory.setWbhLogicBalance(walletBalanceHistoryDetails.getWbhLogicBalance());
	        walletBalanceHistory.setWbhSpecificBalance(walletBalanceHistoryDetails.getWbhSpecificBalance());

	        // Assure-toi que ce type correspond à celui dans ton entité
	        walletBalanceHistory.setWbhLastUpdated(new java.util.Date());

	        return repository.save(walletBalanceHistory);
	    }).orElseThrow(() ->
	        new RuntimeException("Wallet Balance History not found with WBH_CODE: " + wbhCode)
	    );
	}

	@Override
	public void deleteWalletBalanceHistory(Integer wbhCode) {
        repository.deleteById(wbhCode);
		
	}
  @Override
	    public List<WALLET_BALANCE_HISTORY> searchWalletBalanceHistories(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchWalletBalanceHistories(searchWord);
	    }

	
}
