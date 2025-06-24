package com.servicesImp;

import com.model.WALLET_CATEGORY;
import com.repository.WalletCategoryRepository;
import com.service.WalletCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletCategoryServiceImp implements WalletCategoryService {

    @Autowired
    private WalletCategoryRepository walletCategoryRepository;

    // ➤ Créer une nouvelle catégorie de wallet
    @Override
    public WALLET_CATEGORY createWalletCategory(WALLET_CATEGORY walletCategory) {
        return walletCategoryRepository.save(walletCategory);
    }

    // ➤ Récupérer toutes les catégories
    @Override
    public List<WALLET_CATEGORY> getAllWalletCategories() {
        return walletCategoryRepository.findAll();
    }

    // ➤ Récupérer une catégorie par ID
    @Override
    public WALLET_CATEGORY getWalletCategoryById(Integer id) {
        return walletCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet Category not found with id: " + id));
    }

    // ➤ Mettre à jour une catégorie (SANS modifier le code)
    @Override
    public WALLET_CATEGORY updateWalletCategory(Integer id, WALLET_CATEGORY walletCategory) {
        WALLET_CATEGORY existingCategory = walletCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet Category not found with id: " + id));

        // Mise à jour des champs modifiables (seul le code n'est pas modifiable)
        existingCategory.setWcaIden(walletCategory.getWcaIden());
        existingCategory.setWcaLabe(walletCategory.getWcaLabe());
        existingCategory.setWcaFinId(walletCategory.getWcaFinId());

        return walletCategoryRepository.save(existingCategory);
    }

    // ➤ Supprimer une catégorie
    @Override
    public void deleteWalletCategory(Integer id) {
        if (!walletCategoryRepository.existsById(id)) {
            throw new RuntimeException("Wallet Category not found with id: " + id);
        }
        walletCategoryRepository.deleteById(id);
    }

    @Override
    public List<WALLET_CATEGORY> searchWalletCategories(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return walletCategoryRepository.findAll();
        }
        return walletCategoryRepository.searchWalletCategories(searchWord);
    }
}