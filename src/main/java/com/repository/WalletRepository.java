package com.repository;

import com.model.WALLET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WalletRepository extends JpaRepository<WALLET, Integer> {
   // List<WALLET> findByCustomerCode(String customerCode);
   // List<WALLET> findByStatus(String status);
	
	// Recherche des wallets par l'ID du customer
    List<WALLET> findByCustomerCusCode(Integer cusCode);

    // Recherche des wallets associés à un customer via son identifiant (cusIden)
    List<WALLET> findByCustomerCusIden(String cusIden);

    // Recherche des wallets associés à un customer via son email
    List<WALLET> findByCustomerCusMailAddress(String cusMailAddress);

    // Recherche par identifiant WAL_IDEN
    List<WALLET> findByWalIden(String walIden);

    // Recherche par label
    List<WALLET> findByWalLabe(String walLabe);

    // Recherche par WAL_KEY
    List<WALLET> findByWalKey(Integer walKey);

    // Recherche par solde effectif
    List<WALLET> findByWalEffBal(Float walEffBal);

    // Recherche par solde logique
    List<WALLET> findByWalLogicBalance(Float walLogicBalance);

    // Recherche par solde spécifique
    List<WALLET> findByWalSpecificBalance(Float walSpecificBalance);

    // Recherche par code financier
    List<WALLET> findByWalFinId(Integer walFinId);

    // Recherche par code client (CUSTOMER)
    List<WALLET> findByCustomer_CusCode(Integer cusCode); // si le champ s'appelle CUS_CODE dans CUSTOMER

    // Recherche par statut du wallet
    List<WALLET> findByWalletStatus_WstCode(Integer wstCode); // si le champ s'appelle WST_CODE dans WALLET_STATUS

    // Recherche par type de wallet
    List<WALLET> findByWalletType_WtyCode(Integer wtyCode); // si le champ s'appelle WTY_CODE dans WALLET_TYPE

    // Recherche par catégorie de wallet
    List<WALLET> findByWalletCategory_WcaCode(Integer wcaCode); // si le champ s'appelle WCA_CODE dans WALLET_CATEGORY

    // Recherche par date de dernière mise à jour
    List<WALLET> findByLastUpdatedDate(LocalDateTime lastUpdatedDate);
    
    @Query("SELECT w FROM WALLET w WHERE " +
            "LOWER(w.walLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "CAST(w.walCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walKey AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walFinId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walEffBal AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walLogicBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.walSpecificBalance AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(w.lastUpdatedDate AS string) LIKE CONCAT('%', :searchWord, '%')")
     List<WALLET> searchWallets(@Param("searchWord") String searchWord);
    

	
}
