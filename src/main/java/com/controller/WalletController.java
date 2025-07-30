package com.controller;

import com.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.*;
import com.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WalletStatusRepository walletStatusRepository;

    @Autowired
    private WalletTypeRepository walletTypeRepository;

    @Autowired
    private WalletCategoryRepository walletCategoryRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private WalletBalanceHistoryRepository walletBalanceHistoryRepository;

    @Autowired
    private WalletRepository walletRepository;

    @GetMapping("/count")
    public ResponseEntity<Long> getWalletCount() {
        long count = walletRepository.count();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/count/active")
    public ResponseEntity<Long> getActiveWalletCount() {
        return new ResponseEntity<>(walletService.countActiveWallets(), HttpStatus.OK);
    }
    @GetMapping("/count/pending")
    public ResponseEntity<Long> getPendingWalletCount() {
        return new ResponseEntity<>(walletService.countPendingWallets(), HttpStatus.OK);
    }


    // 🔹 Récupérer tous les wallets
    @GetMapping
    public ResponseEntity<List<WALLET>> getAllWallets() {
        List<WALLET> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

    // 🔹 Récupérer un wallet par ID
    @GetMapping("/{id}")
    public ResponseEntity<WALLET> getWalletById(@PathVariable Integer id) {
        Optional<WALLET> wallet = walletService.getWalletById(id);
        return wallet.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Créer un nouveau wallet
    /*
     * @PostMapping
     * public ResponseEntity<WALLET> createWallet(@Valid @RequestBody WALLET wallet)
     * {
     * // Vérification et récupération des entités liées avant de créer le wallet
     * Optional<CUSTOMER> customerOpt =
     * customerRepository.findById(wallet.getCustomer().getCusCode());
     * Optional<WALLET_STATUS> walletStatusOpt =
     * walletStatusRepository.findById(wallet.getWalletStatus().getWstCode());
     * Optional<WALLET_TYPE> walletTypeOpt =
     * walletTypeRepository.findById(wallet.getWalletType().getWtyCode());
     * Optional<WALLET_CATEGORY> walletCategoryOpt =
     * walletCategoryRepository.findById(wallet.getWalletCategory().getWcaCode());
     * Optional<CARD_LIST> cardListOpt =
     * cardListRepository.findById(wallet.getCardList() != null ?
     * wallet.getCardList().getCliCode() : null);
     * 
     * Optional<WALLET_BALANCE_HISTORY> lastBalanceHistoryOpt = Optional.empty();
     * 
     * if (wallet.getLastBalanceHistory() != null &&
     * wallet.getLastBalanceHistory().getWbhCode() != null) {
     * lastBalanceHistoryOpt = walletBalanceHistoryRepository.findById(
     * wallet.getLastBalanceHistory().getWbhCode()
     * );
     * }
     * 
     * // Ici tu ajoutes ce bloc :
     * if (lastBalanceHistoryOpt.isPresent()) {
     * wallet.setLastBalanceHistory(lastBalanceHistoryOpt.get());
     * } else {
     * wallet.setLastBalanceHistory(null); // ou ne rien faire si tu veux garder
     * l'ancien comportement
     * }
     * 
     * // Si une entité liée est introuvable, renvoyer une erreur
     * if (customerOpt.isEmpty() || walletStatusOpt.isEmpty() ||
     * walletTypeOpt.isEmpty() || walletCategoryOpt.isEmpty()) {
     * return ResponseEntity.badRequest().body(null);
     * }
     * 
     * // Associer les entités liées au wallet
     * wallet.setCustomer(customerOpt.get());
     * wallet.setWalletStatus(walletStatusOpt.get());
     * wallet.setWalletType(walletTypeOpt.get());
     * wallet.setWalletCategory(walletCategoryOpt.get());
     * 
     * // Associer le cardList si présent
     * if (cardListOpt.isPresent()) {
     * wallet.setCardList(cardListOpt.get());
     * }
     * 
     * // Associer le dernier solde historique si présent
     * if (lastBalanceHistoryOpt.isPresent()) {
     * wallet.setLastBalanceHistory(lastBalanceHistoryOpt.get());
     * }
     * 
     * // Créer le wallet avec toutes les relations associées
     * WALLET newWallet = walletService.createWallet(wallet);
     * 
     * // Si le solde historique est absent, en créer un nouveau
     * if (wallet.getLastBalanceHistory() == null) {
     * WALLET_BALANCE_HISTORY walletBalanceHistory = new WALLET_BALANCE_HISTORY();
     * walletBalanceHistory.setWallet(newWallet);
     * walletBalanceHistory.setWbhAmount(newWallet.getWalEffBal()); // Utiliser le
     * solde actuel
     * walletBalanceHistory.setWbhLastUpdated(new
     * java.sql.Date(System.currentTimeMillis())); // Date actuelle ou selon besoin
     * walletBalanceHistoryRepository.save(walletBalanceHistory); // Enregistrer
     * l'historique
     * }
     * 
     * // Retourner la réponse
     * return ResponseEntity.status(HttpStatus.CREATED).body(newWallet);
     * }
     */

    // Créer un nouveau wallet
    /*     @PostMapping
    public ResponseEntity<?> createWallet(@Valid @RequestBody WALLET wallet) {
        if(wallet.getCustomer() == null)
            return ResponseEntity.badRequest().body("customer not found");
        return ResponseEntity.ok().body(walletService.createWallet(wallet));
    } */

    @PostMapping
    public ResponseEntity<WALLET> createWallet(@Valid @RequestBody WALLET wallet) {
        // Vérification et récupération des entités liées avant de créer le wallet
        Optional<CUSTOMER> customerOpt = customerRepository.findById(wallet.getCustomer().getCusCode());
        Optional<WALLET_STATUS> walletStatusOpt = walletStatusRepository
                .findById( wallet.getWalletStatus().getWstCode());
        Optional<WALLET_TYPE> walletTypeOpt = walletTypeRepository.findById(wallet.getWalletType().getWtyCode());
        Optional<WALLET_CATEGORY> walletCategoryOpt = walletCategoryRepository
                .findById(wallet.getWalletCategory().getWcaCode());
        Optional<CARD_LIST> cardListOpt = wallet.getCardList() != null
                ? cardListRepository.findById(wallet.getCardList().getCliCode())
                : Optional.empty();

        // Vérification des dépendances
        if (customerOpt.isEmpty() || walletStatusOpt.isEmpty() || walletTypeOpt.isEmpty()/* 
                || walletCategoryOpt.isEmpty() */) {
            return ResponseEntity.badRequest().body(null);
        }

        // Association des entités liées
        wallet.setCustomer(customerOpt.get());
        wallet.setWalletStatus(walletStatusOpt.get());
        wallet.setWalletType(walletTypeOpt.get());
        //wallet.setWalletCategory(walletCategoryOpt.get());
        //cardListOpt.ifPresent(wallet::setCardList);

        // Étape 1 : création initiale du wallet
        WALLET savedWallet = walletService.createWallet(wallet);

        // Étape 2 : création de l'entrée historique
        WALLET_BALANCE_HISTORY walletBalanceHistory = new WALLET_BALANCE_HISTORY();
        walletBalanceHistory.setWallet(savedWallet);
        walletBalanceHistory.setWbhEffBal(savedWallet.getWalEffBal());
        walletBalanceHistory.setWbhLogicBalance(savedWallet.getWalLogicBalance());
        walletBalanceHistory.setWbhSpecificBalance(savedWallet.getWalSpecificBalance());
        walletBalanceHistory.setWbhLastUpdated(new java.util.Date());
        walletBalanceHistory.setWbhIden(0); // ou générer dynamiquement

        walletBalanceHistoryRepository.save(walletBalanceHistory);

        // Étape 3 : mise à jour du wallet avec référence vers l'historique
        savedWallet.setLastBalanceHistory(walletBalanceHistory);
        WALLET updatedWallet = walletService.createWallet(savedWallet);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedWallet);
    }


    // 🔹 Mettre à jour un wallet existant
    @PutMapping("/{id}")
    public ResponseEntity<WALLET> updateWallet(@PathVariable Integer id, @Valid @RequestBody WALLET walletDetails) {
        Optional<WALLET> optionalWallet = walletService.getWalletById(id);
        if (optionalWallet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        WALLET existingWallet = optionalWallet.get();

        // Mettre à jour les champs du wallet (à adapter selon les besoins)
        existingWallet.setWalEffBal(walletDetails.getWalEffBal());
        existingWallet.setWalLogicBalance(walletDetails.getWalLogicBalance());
        existingWallet.setWalSpecificBalance(walletDetails.getWalSpecificBalance());
        existingWallet.setWalletStatus(walletDetails.getWalletStatus());
        existingWallet.setWalletType(walletDetails.getWalletType());
        existingWallet.setWalletCategory(walletDetails.getWalletCategory());

        WALLET updatedWallet = walletService.createWallet(existingWallet);

        // Mettre à jour l'historique du solde
        WALLET_BALANCE_HISTORY balanceHistory = updatedWallet.getLastBalanceHistory();
        if (balanceHistory != null) {
            balanceHistory.setWbhEffBal(updatedWallet.getWalEffBal());
            balanceHistory.setWbhLogicBalance(updatedWallet.getWalLogicBalance());
            balanceHistory.setWbhSpecificBalance(updatedWallet.getWalSpecificBalance());
            balanceHistory.setWbhLastUpdated(new java.util.Date());
            walletBalanceHistoryRepository.save(balanceHistory);
        }

        return ResponseEntity.ok(updatedWallet);
    }

    // 🔹 Supprimer un wallet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Integer id) {
        if (walletService.getWalletById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer les wallets d’un client donné
    /*
     * @GetMapping("/customer/{customerCode}")
     * public ResponseEntity<List<WALLET>> getWalletsByCustomerCode(@PathVariable
     * String customerCode) {
     * List<WALLET> wallets = walletService.getWalletsByCustomerCode(customerCode);
     * return wallets.isEmpty() ? ResponseEntity.notFound().build() :
     * ResponseEntity.ok(wallets);
     * }
     */

    // 🔹 Récupérer les wallets par statut
    /*
     * @GetMapping("/status/{status}")
     * public ResponseEntity<List<WALLET>> getWalletsByStatus(@PathVariable String
     * status) {
     * List<WALLET> wallets = walletService.getWalletsByStatus(status);
     * return wallets.isEmpty() ? ResponseEntity.notFound().build() :
     * ResponseEntity.ok(wallets);
     * }
     */

    @GetMapping("/by-customer-code/{code}")
    public ResponseEntity<WALLET> getWalletsByCustomerCode(@PathVariable Integer code) {
        return ResponseEntity.ok().body(walletService.searchByCustomerCusCode(code));
    }

    @GetMapping("/by-customer-iden/{iden}")
    public WALLET getWalletsByCustomerIden(@PathVariable String iden) {
        return walletService.searchByCustomerCusIden(iden);
    }

    @GetMapping("/by-customer-mail/{mail}")
    public WALLET getWalletsByCustomerMail(@PathVariable String mail) {
        return walletService.searchByCustomerCusMailAddress(mail);
    }

    @GetMapping("/by-iden/{walIden}")
    public ResponseEntity<List<WALLET>> getByWalIden(@PathVariable String walIden) {
        List<WALLET> wallets = walletService.findByWalIden(walIden);
        return wallets.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(wallets);
    }

    @GetMapping("/by-label/{label}")
    public ResponseEntity<List<WALLET>> getByWalLabe(@PathVariable String label) {
        return ResponseEntity.ok(walletService.findByWalLabe(label));
    }

    @GetMapping("/by-key/{key}")
    public ResponseEntity<List<WALLET>> getByWalKey(@PathVariable Integer key) {
        return ResponseEntity.ok(walletService.findByWalKey(key));
    }

    @GetMapping("/by-eff-bal/{effBal}")
    public ResponseEntity<List<WALLET>> getByEffBal(@PathVariable Float effBal) {
        return ResponseEntity.ok(walletService.findByWalEffBal(effBal));
    }

    @GetMapping("/by-logic-bal/{logicBal}")
    public ResponseEntity<List<WALLET>> getByLogicBal(@PathVariable Float logicBal) {
        return ResponseEntity.ok(walletService.findByWalLogicBalance(logicBal));
    }

    @GetMapping("/by-specific-bal/{specificBal}")
    public ResponseEntity<List<WALLET>> getBySpecificBal(@PathVariable Float specificBal) {
        return ResponseEntity.ok(walletService.findByWalSpecificBalance(specificBal));
    }

    @GetMapping("/by-fin-id/{finId}")
    public ResponseEntity<List<WALLET>> getByFinId(@PathVariable Integer finId) {
        return ResponseEntity.ok(walletService.findByWalFinId(finId));
    }

    @GetMapping("/by-customer/{cusCode}")
    public ResponseEntity<WALLET> getByCustomer(@PathVariable Integer cusCode) {
        return ResponseEntity.ok(walletService.findByCustomer_CusCode(cusCode));
    }

    @GetMapping("/by-status/{wstCode}")
    public ResponseEntity<List<WALLET>> getByStatus(@PathVariable Integer wstCode) {
        return ResponseEntity.ok(walletService.findByWalletStatus_WstCode(wstCode));
    }

    @GetMapping("/by-type/{wtyCode}")
    public ResponseEntity<List<WALLET>> getByType(@PathVariable Integer wtyCode) {
        return ResponseEntity.ok(walletService.findByWalletType_WtyCode(wtyCode));
    }

    @GetMapping("/by-category/{wcaCode}")
    public ResponseEntity<List<WALLET>> getByCategory(@PathVariable Integer wcaCode) {
        return ResponseEntity.ok(walletService.findByWalletCategory_WcaCode(wcaCode));
    }

    @GetMapping("/by-last-update/{lastUpdate}")
    public ResponseEntity<List<WALLET>> getByLastUpdated(@PathVariable String lastUpdate) {
        // Supprimer les guillemets autour de la chaîne, si présents
        lastUpdate = lastUpdate.replace("\"", "");

        // Adapter le format si besoin
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime parsedDate = LocalDateTime.parse(lastUpdate, formatter);

        List<WALLET> wallets = walletService.findByLastUpdatedDate(parsedDate);
        return ResponseEntity.ok(wallets);
    }

    @DeleteMapping("/by-iden/{walIden}")
    public ResponseEntity<Void> deleteWalletByWalIden(@PathVariable String walIden) {
        List<WALLET> wallets = walletRepository.findByWalIden(walIden);
        if (!wallets.isEmpty()) {
            walletService.deleteByWalIden(walIden);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /* @GetMapping("/by-customer/{cusCode}/status")
    public ResponseEntity<WALLET_STATUS> getWalletStatusByCusCode(@PathVariable Integer cusCode) {
        List<WALLET> wallets = walletService.findByCustomer_CusCode(cusCode);
        if (wallets.isEmpty() || wallets.get(0).getWalletStatus() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wallets.get(0).getWalletStatus());
    } */

    @GetMapping("/search")
    public ResponseEntity<List<WALLET>> searchWallets(@RequestParam("word") String searchWord) {
        List<WALLET> wallets = walletService.searchWallets(searchWord);
        return ResponseEntity.ok(wallets);
    }
    
 // WalletController.java
    @GetMapping("/count/by-category")
    public ResponseEntity<Map<String, Long>> getWalletCountByCategory() {
        Map<String, Long> categoryCounts = walletService.getWalletCountByCategory();
        return ResponseEntity.ok(categoryCounts);
    }

}
