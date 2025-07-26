package com.service;
import com.model.CUSTOMER;
import java.util.List;
import java.util.Optional;
public interface CustomerService {
	
	// 🔹 Créer un client
    CUSTOMER createCustomer(CUSTOMER customer);

    // 🔹 Récupérer un client par son code
    Optional<CUSTOMER> getCustomerById(Integer cusCode);

    // 🔹 Récupérer tous les clients
    List<CUSTOMER> getAllCustomers();

    // 🔹 Modifier un client existant
    CUSTOMER updateCustomer(Integer cusCode, CUSTOMER customerDetails);

    // 🔹 Supprimer un client
    void deleteCustomer(Integer cusCode);

    // 🔹 Vérifier si un client existe
    boolean existsById(Integer cusCode);

    // 🔹 Récupérer un client par email
    Optional<CUSTOMER> getCustomerByEmail(String email);

    // 🔹 Récupérer un client par téléphone
    Optional<CUSTOMER> getCustomerByPhone(String phone);

    // 🔹 Récupérer les clients par ville
    List<CUSTOMER> getCustomersByCity(Integer cityCode);

    // 🔹 Récupérer les clients par pays
    List<CUSTOMER> getCustomersByCountry(Integer countryCode);

    // 🔹 Récupérer les clients ayant un portefeuille
    //List<CUSTOMER> getCustomersWithWallets();

    // 🔹 Récupérer les clients sans portefeuille
    //List<CUSTOMER> getCustomersWithoutWallets();

    // 🔹 Recherche avancée avec filtres dynamiques
    //List<CUSTOMER> searchCustomers(String name, String email, String phone, Integer cityCode, Integer countryCode);
    List<CUSTOMER> searchCustomers(String searchWord);

	boolean comapreTOTP(String cusMailAdress, String totp);

    List<CUSTOMER> getCustomersByStatus(Integer statusCode);
    
    long getTotalCustomerCount();
    long getActiveCustomerCount(Integer statusCode);
    
    long getNewCustomersToday();
	

}
