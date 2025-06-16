package com.controller;
import com.model.WALLET_CATEGORY;
import com.service.WalletCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet-categories")
public class WalletCategoryController {
	
	@Autowired
    private WalletCategoryService walletCategoryService;

    // ➤ Récupérer toutes les catégories
    @GetMapping
    public ResponseEntity<List<WALLET_CATEGORY>> getAllWalletCategories() {
        List<WALLET_CATEGORY> categories = walletCategoryService.getAllWalletCategories();
        return ResponseEntity.ok(categories);
    }

    // ➤ Récupérer une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<WALLET_CATEGORY> getWalletCategoryById(@PathVariable Integer id) {
        WALLET_CATEGORY category = walletCategoryService.getWalletCategoryById(id);
        return ResponseEntity.ok(category);
    }

    // ➤ Créer une nouvelle catégorie
    @PostMapping
    public ResponseEntity<WALLET_CATEGORY> createWalletCategory(@RequestBody WALLET_CATEGORY walletCategory) {
        WALLET_CATEGORY createdCategory = walletCategoryService.createWalletCategory(walletCategory);
        return ResponseEntity.ok(createdCategory);
    }

    // ➤ Mettre à jour une catégorie (SANS modifier le code)
    @PutMapping("/{id}")
    public ResponseEntity<WALLET_CATEGORY> updateWalletCategory(@PathVariable Integer id, 
                                                                @RequestBody WALLET_CATEGORY walletCategory) {
        WALLET_CATEGORY updatedCategory = walletCategoryService.updateWalletCategory(id, walletCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    // ➤ Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWalletCategory(@PathVariable Integer id) {
        walletCategoryService.deleteWalletCategory(id);
        return ResponseEntity.ok("Wallet Category deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<WALLET_CATEGORY>> searchWalletCategories(@RequestParam("word") String searchWord) {
        List<WALLET_CATEGORY> walletCategories = walletCategoryService.searchWalletCategories(searchWord);
        return ResponseEntity.ok(walletCategories);
    }

}
