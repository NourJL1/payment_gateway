package com.servicesImp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.CARD_LIST;
import com.model.WALLET;
import com.service.WalletService;

import jakarta.persistence.EntityNotFoundException;

import com.repository.CardListRepository;
import com.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Override
    public WALLET createWallet(WALLET wallet) {
        logger.debug("Creating wallet: {}", wallet);
        if (wallet.getCardList() != null && wallet.getCardList().getCliCode() != null) {
            CARD_LIST existingCardList = cardListRepository.findById(wallet.getCardList().getCliCode())
                    .orElseThrow(() -> new RuntimeException("CARD_LIST not found"));
            wallet.setCardList(existingCardList);
        }

        if (wallet.getLastBalanceHistory() == null) {
            wallet.setLastBalanceHistory(null);
        }

        WALLET savedWallet = walletRepository.save(wallet);
        logger.info("Wallet created with walCode: {}", savedWallet.getWalCode());
        return savedWallet;
    }

    @Override
    public Optional<WALLET> getWalletById(Integer walCode) {
        logger.debug("Fetching wallet with walCode: {}", walCode);
        return walletRepository.findById(walCode);
    }

    @Override
    public List<WALLET> getAllWallets() {
        logger.debug("Fetching all wallets");
        List<WALLET> wallets = walletRepository.findAll();
        logger.info("Retrieved {} wallets", wallets.size());
        return wallets;
    }

    @Override
    public WALLET updateWallet(Integer walCode, WALLET wallet) {
        logger.debug("Updating wallet with walCode: {}", walCode);
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
            WALLET savedWallet = walletRepository.save(updatedWallet);
            logger.info("Wallet updated with walCode: {}", savedWallet.getWalCode());
            return savedWallet;
        }
        logger.warn("Wallet with walCode {} not found", walCode);
        return null;
    }

    @Override
    public void deleteWallet(Integer walCode) {
        logger.debug("Deleting wallet with walCode: {}", walCode);
        walletRepository.deleteById(walCode);
        logger.info("Wallet deleted with walCode: {}", walCode);
    }

    @Override
    public List<WALLET> searchByCustomerCusCode(Integer cusCode) {
        logger.debug("Searching wallets by customer cusCode: {}", cusCode);
        return walletRepository.findByCustomerCusCode(cusCode);
    }

    @Override
    public List<WALLET> searchByCustomerCusIden(String cusIden) {
        logger.debug("Searching wallets by customer cusIden: {}", cusIden);
        return walletRepository.findByCustomerCusIden(cusIden);
    }

    @Override
    public List<WALLET> searchByCustomerCusMailAddress(String cusMailAddress) {
        logger.debug("Searching wallets by customer cusMailAddress: {}", cusMailAddress);
        return walletRepository.findByCustomerCusMailAddress(cusMailAddress);
    }

    @Override
    public List<WALLET> findByWalIden(String walIden) {
        logger.debug("Searching wallets by walIden: {}", walIden);
        return walletRepository.findByWalIden(walIden);
    }

    @Override
    public List<WALLET> findByWalLabe(String walLabe) {
        logger.debug("Searching wallets by walLabe: {}", walLabe);
        return walletRepository.findByWalLabe(walLabe);
    }

    @Override
    public List<WALLET> findByWalKey(Integer walKey) {
        logger.debug("Searching wallets by walKey: {}", walKey);
        return walletRepository.findByWalKey(walKey);
    }

    @Override
    public List<WALLET> findByWalEffBal(Float walEffBal) {
        logger.debug("Searching wallets by walEffBal: {}", walEffBal);
        return walletRepository.findByWalEffBal(walEffBal);
    }

    @Override
    public List<WALLET> findByWalLogicBalance(Float walLogicBalance) {
        logger.debug("Searching wallets by walLogicBalance: {}", walLogicBalance);
        return walletRepository.findByWalLogicBalance(walLogicBalance);
    }

    @Override
    public List<WALLET> findByWalSpecificBalance(Float walSpecificBalance) {
        logger.debug("Searching wallets by walSpecificBalance: {}", walSpecificBalance);
        return walletRepository.findByWalSpecificBalance(walSpecificBalance);
    }

    @Override
    public List<WALLET> findByWalFinId(Integer walFinId) {
        logger.debug("Searching wallets by walFinId: {}", walFinId);
        return walletRepository.findByWalFinId(walFinId);
    }

    @Override
    public List<WALLET> findByCustomer_CusCode(Integer cusCode) {
        logger.debug("Searching wallets by customer cusCode: {}", cusCode);
        return walletRepository.findByCustomer_CusCode(cusCode);
    }

    @Override
    public List<WALLET> findByWalletStatus_WstCode(Integer wstCode) {
        logger.debug("Searching wallets by walletStatus wstCode: {}", wstCode);
        return walletRepository.findByWalletStatus_WstCode(wstCode);
    }

    @Override
    public List<WALLET> findByWalletType_WtyCode(Integer wtyCode) {
        logger.debug("Searching wallets by walletType wtyCode: {}", wtyCode);
        return walletRepository.findByWalletType_WtyCode(wtyCode);
    }

    @Override
    public List<WALLET> findByWalletCategory_WcaCode(Integer wcaCode) {
        logger.debug("Searching wallets by walletCategory wcaCode: {}", wcaCode);
        return walletRepository.findByWalletCategory_WcaCode(wcaCode);
    }

    @Override
    public List<WALLET> findByLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        logger.debug("Searching wallets by lastUpdatedDate: {}", lastUpdatedDate);
        return walletRepository.findByLastUpdatedDate(lastUpdatedDate);
    }

    @Override
    public void deleteByWalIden(String walIden) {
        logger.debug("Deleting wallets by walIden: {}", walIden);
        List<WALLET> wallets = walletRepository.findByWalIden(walIden);
        if (!wallets.isEmpty()) {
            walletRepository.deleteAll(wallets);
            logger.info("Wallets deleted for walIden: {}", walIden);
        } else {
            logger.warn("No wallets found for walIden: {}", walIden);
            throw new EntityNotFoundException("Wallet with walIden " + walIden + " not found");
        }
    }

    @Override
    public List<WALLET> searchWallets(String searchWord) {
        logger.debug("Searching wallets with searchWord: {}", searchWord);
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return walletRepository.findAll();
        }
        return walletRepository.searchWallets(searchWord);
    }

    @Override
    public Long countActiveWallets() {
        logger.debug("Executing countActiveWallets query for wstCode = 1");
        Long count = walletRepository.countActiveWallets();
        logger.info("countActiveWallets: Returned count = {}", count);
        return count;
    }
}