package com.repository;

import com.model.WALLET;
import org.springframework.data.jpa.repository.JpaRepository;
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

    
	
}
