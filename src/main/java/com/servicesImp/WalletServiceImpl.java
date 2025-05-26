package com.servicesImp;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CARD_LIST;
import com.model.WALLET;
import com.service.WalletService;
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
}
