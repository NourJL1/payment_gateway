package com.service;
import java.util.List;
import java.util.Optional;
import com.model.WALLET_OPERATIONS;

public interface WalletOperationsService {
	// 🔹 Récupérer toutes les opérations
    List<WALLET_OPERATIONS> getAllOperations();

    // 🔹 Récupérer une opération par son identifiant unique
    Optional<WALLET_OPERATIONS> getOperationById(Integer WOP_CODE);

    // 🔹 Créer une nouvelle opération
    WALLET_OPERATIONS createOperation(WALLET_OPERATIONS operation);

    // 🔹 Mettre à jour une opération existante
    WALLET_OPERATIONS updateOperation(Integer WOP_CODE, WALLET_OPERATIONS operationDetails);

    // 🔹 Supprimer une opération
    void deleteOperation(Integer WOP_CODE);

    // 🔹 Récupérer les opérations d’un portefeuille donné
   /* List<WALLET_OPERATIONS> getOperationsByWalletCode(Integer walletCode);

    // 🔹 Récupérer les opérations d’un client donné
    List<WALLET_OPERATIONS> getOperationsByCustomerCode(Integer customerCode);

    // 🔹 Récupérer les opérations selon leur statut
    List<WALLET_OPERATIONS> getOperationsByStatus(String status);

    // 🔹 Récupérer les opérations d’un portefeuille triées par date décroissante
    List<WALLET_OPERATIONS> getOperationsByWalletSortedByTimestamp(Integer walletCode);*/
}
