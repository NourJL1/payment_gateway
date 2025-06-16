package com.servicesImp;
import com.model.WALLET_STATUS;
import com.repository.WalletOperationsRepository;
import com.repository.WalletStatusRepository;
import com.service.WalletStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WalletStatusServiceImp implements WalletStatusService {
	 @Autowired
	    private WalletStatusRepository repository;
	@Override
	public List<WALLET_STATUS> getAllWalletStatuses() {
        return repository.findAll();

	}

	@Override
	public Optional<WALLET_STATUS> getWalletStatusById(Integer wstCode) {
        return repository.findById(wstCode);

	}

	@Override
	public WALLET_STATUS createWalletStatus(WALLET_STATUS walletStatus) {
        return repository.save(walletStatus);

	}

	@Override
	public WALLET_STATUS updateWalletStatus(Integer wstCode, WALLET_STATUS walletStatusDetails) {
		return repository.findById(wstCode).map(walletStatus -> {
            walletStatus.setWstIden(walletStatusDetails.getWstIden());
            walletStatus.setWstLabe(walletStatusDetails.getWstLabe());
            return repository.save(walletStatus);
        }).orElseThrow(() -> new RuntimeException("Wallet Status not found with WST_CODE: " + wstCode));
	}

	@Override
	public void deleteWalletStatus(Integer wstCode) {
        repository.deleteById(wstCode);
		
	}
   @Override
	    public List<WALLET_STATUS> searchWalletStatuses(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchWalletStatuses(searchWord);
	    }

}