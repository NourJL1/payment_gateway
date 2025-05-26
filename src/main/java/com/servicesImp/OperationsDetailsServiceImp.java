package com.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.model.OPERATION_DETAILS;
import com.model.WALLET_OPERATIONS;
import com.service.OperationDetailsService;
import com.repository.OperationsDetailsRepository;
import com.repository.WalletOperationsRepository;

@Service
public class OperationsDetailsServiceImp implements OperationDetailsService {

    @Autowired
    private OperationsDetailsRepository operationDetailsRepository;
    
    @Autowired 
    private WalletOperationsRepository walletOperationsRepository;

    @Override
    public List<OPERATION_DETAILS> findAll() {
        return operationDetailsRepository.findAll();
    }

    @Override
    public Optional<OPERATION_DETAILS> findById(Integer id) {
        return operationDetailsRepository.findById(id);
    }

    @Override
    public OPERATION_DETAILS save(OPERATION_DETAILS operationDetails) {

        // Vérifie que l'objet walletOperation est bien référencé
        WALLET_OPERATIONS walletOp = operationDetails.getWalletOperation();

        if (walletOp == null || walletOp.getWopCode() == null) {
            throw new IllegalArgumentException("walletOperation est requis et doit avoir un ID valide");
        }

        // Vérifie s'il existe en base
        WALLET_OPERATIONS persistedWalletOp = walletOperationsRepository.findById(walletOp.getWopCode())
            .orElseThrow(() -> new IllegalArgumentException("walletOperation avec ID " + walletOp.getWopCode() + " n'existe pas"));

        // Réassigne l'objet persisté à l'entité
        operationDetails.setWalletOperation(persistedWalletOp);

        return operationDetailsRepository.save(operationDetails);
    }


    @Override
    public void deleteById(Integer id) {
        operationDetailsRepository.deleteById(id);
    }
}
