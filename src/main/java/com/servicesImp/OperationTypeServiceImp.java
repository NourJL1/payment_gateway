package com.servicesImp;
import com.repository.FeeSchemaRepository;
import com.repository.OperationTypeRepository;
import com.repository.WalletCategoryRepository;
import com.repository.WalletRepository;
import com.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.model.*;

@Service
public class OperationTypeServiceImp implements OperationTypeService {
	
	@Autowired 
	private FeeSchemaRepository feeSchemaRepository;
	
	@Autowired 
	private WalletRepository walletRepository;
	

	@Autowired
    private OperationTypeRepository operationTypeRepository;
	
	@Autowired 
	private WalletCategoryRepository walletCategoryRepository;

    @Override
    public List<OPERATION_TYPE> getAllOperationTypes() {
        return operationTypeRepository.findAll();
    }

    @Override
    public OPERATION_TYPE getOperationTypeById(Integer id) {
        Optional<OPERATION_TYPE> operationType = operationTypeRepository.findById(id);
        return operationType.orElseThrow(() -> new RuntimeException("Operation Type not found"));
    }

    public OPERATION_TYPE createOperationType(OPERATION_TYPE operationType) {
        // Charger le FeeSchema complet
        if (operationType.getFeeSchema() != null) {
            Integer fscCode = operationType.getFeeSchema().getFscCode();
            FEE_SCHEMA feeSchema = feeSchemaRepository.findById(fscCode)
                    .orElseThrow(() -> new RuntimeException("FeeSchema not found"));
            operationType.setFeeSchema(feeSchema);
        }

        // Charger Wallet s’il est défini
        if (operationType.getWallet() != null) {
            Integer walCode = operationType.getWallet().getWalCode();
            WALLET wallet = walletRepository.findById(walCode)
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));
            operationType.setWallet(wallet);
        }

        // Charger WalletCategory s’il est défini
        if (operationType.getWalletCategory() != null) {
            Integer wcaCode = operationType.getWalletCategory().getWcaCode();
            WALLET_CATEGORY category = walletCategoryRepository.findById(wcaCode)
                    .orElseThrow(() -> new RuntimeException("Wallet Category not found"));
            operationType.setWalletCategory(category);
        }

        return operationTypeRepository.save(operationType);
    }


    @Override
    public OPERATION_TYPE updateOperationType(Integer id, OPERATION_TYPE operationType) {
        OPERATION_TYPE existingOpType = getOperationTypeById(id);

        // On met à jour seulement les attributs modifiables
        existingOpType.setOptIden(operationType.getOptIden());
        existingOpType.setOptLabe(operationType.getOptLabe());
        existingOpType.setOptFscIden(operationType.getOptFscIden());
        existingOpType.setOptFscLab(operationType.getOptFscLab());
        existingOpType.setWallet(operationType.getWallet());
        existingOpType.setWalletCategory(operationType.getWalletCategory());

        return operationTypeRepository.save(existingOpType);
    }

    @Override
    public void deleteOperationType(Integer id) {
        operationTypeRepository.deleteById(id);
    }

    @Override
    public List<OPERATION_TYPE> searchOperationTypes(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return operationTypeRepository.findAll();
        }
        return operationTypeRepository.searchOperationTypes(searchWord);
    }
	
}
