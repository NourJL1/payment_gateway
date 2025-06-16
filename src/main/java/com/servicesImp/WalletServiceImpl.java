package com.servicesImp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CARD_LIST;
import com.model.WALLET;
import com.service.WalletService;

import jakarta.persistence.EntityNotFoundException;

import com.repository.CardListRepository;
import com.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Override
    public WALLET createWallet(WALLET wallet) {
        // Vérifier et associer le cardList si nécessaire
        if (wallet.getCardList() != null && wallet.getCardList().getCliCode() != null) {
            CARD_LIST existingCardList = cardListRepository.findById(wallet.getCardList().getCliCode())
                    .orElseThrow(() -> new RuntimeException("CARD_LIST not found"));
            wallet.setCardList(existingCardList);
        }

        // Vérifier si le WalletBalanceHistory est null, et ne pas causer de problème
        if (wallet.getLastBalanceHistory() == null) {
            wallet.setLastBalanceHistory(null); // Explicitement assigner null si c'est le cas
        }

        // Sauvegarder la wallet dans la base de données
        return walletRepository.save(wallet);
    }


    @Override
    public Optional<WALLET> getWalletById(Integer walCode) {
        return walletRepository.findById(walCode);
    }

    @Override
    public List<WALLET> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public WALLET updateWallet(Integer walCode, WALLET wallet) {
        Optional<WALLET> existingWallet = walletRepository.findById(walCode);
        if (existingWallet.isPresent()) {
            WALLET updatedWallet = existingWallet.get();
            updatedWallet.setWalLabe(wallet.getWalLabe());
            updatedWallet.setWalEffBal(wallet.getWalEffBal());
            updatedWallet.setWalLogicBalance(wallet.getWalLogicBalance());
            updatedWallet.setWalSpecificBalance(wallet.getWalSpecificBalance());
            updatedWallet.setLastUpdatedDate(wallet.getLastUpdatedDate());
            updatedWallet.setCustomer(wallet.getCustomer());
            updatedWallet.setWalletStatus(wallet.getWalletStatus());
            updatedWallet.setWalletType(wallet.getWalletType());
            updatedWallet.setWalletCategory(wallet.getWalletCategory());
            return walletRepository.save(updatedWallet);
        }
        return null;
    }

    @Override
    public void deleteWallet(Integer walCode) {
        walletRepository.deleteById(walCode);
    }


	@Override
	public List<WALLET> searchByCustomerCusCode(Integer cusCode) {
        return walletRepository.findByCustomerCusCode(cusCode);

	}


	@Override
	public List<WALLET> searchByCustomerCusIden(String cusIden) {
        return walletRepository.findByCustomerCusIden(cusIden);

	}


	@Override
	public List<WALLET> searchByCustomerCusMailAddress(String cusMailAddress) {
        return walletRepository.findByCustomerCusMailAddress(cusMailAddress);

	}


	
    /*@Override
    public List<WALLET> getWalletsByCustomerCode(String customerCode) {
        return walletRepository.findByCustomerCode(customerCode);
    }*/

   /* @Override
    public List<WALLET> getWalletsByStatus(String status) {
        return walletRepository.findByStatus(status);
    }*/
    @Override
	public List<WALLET> findByWalIden(String walIden) {
        return walletRepository.findByWalIden(walIden);

	}


	@Override
	public List<WALLET> findByWalLabe(String walLabe) {
        return walletRepository.findByWalLabe(walLabe);

	}


	@Override
	public List<WALLET> findByWalKey(Integer walKey) {
        return walletRepository.findByWalKey(walKey);

	}


	@Override
	public List<WALLET> findByWalEffBal(Float walEffBal) {
        return walletRepository.findByWalEffBal(walEffBal);

	}


	@Override
	public List<WALLET> findByWalLogicBalance(Float walLogicBalance) {
        return walletRepository.findByWalLogicBalance(walLogicBalance);

	}


	@Override
	public List<WALLET> findByWalSpecificBalance(Float walSpecificBalance) {
        return walletRepository.findByWalSpecificBalance(walSpecificBalance);

	}


	@Override
	public List<WALLET> findByWalFinId(Integer walFinId) {
        return walletRepository.findByWalFinId(walFinId);

	}


	@Override
	public List<WALLET> findByCustomer_CusCode(Integer cusCode) {
        return walletRepository.findByCustomer_CusCode(cusCode);

	}


	@Override
	public List<WALLET> findByWalletStatus_WstCode(Integer wstCode) {
        return walletRepository.findByWalletStatus_WstCode(wstCode);

	}


	@Override
	public List<WALLET> findByWalletType_WtyCode(Integer wtyCode) {
        return walletRepository.findByWalletType_WtyCode(wtyCode);

	}


	@Override
	public List<WALLET> findByWalletCategory_WcaCode(Integer wcaCode) {
        return walletRepository.findByWalletCategory_WcaCode(wcaCode);

	}


	@Override
	public List<WALLET> findByLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        return walletRepository.findByLastUpdatedDate(lastUpdatedDate);

	}


	@Override
	public void deleteByWalIden(String walIden) {
		List<WALLET> wallets = walletRepository.findByWalIden(walIden);
	    if (!wallets.isEmpty()) {
	        walletRepository.deleteAll(wallets);
	    } else {
	        throw new EntityNotFoundException("Wallet with walIden " + walIden + " not found");
	    }
		
	}
	
	 @Override
	    public List<WALLET> searchWallets(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return walletRepository.findAll();
	        }
	        return walletRepository.searchWallets(searchWord);
	    }
}
