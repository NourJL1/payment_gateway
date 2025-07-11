package com.servicesImp;

import com.model.FEES;
import com.model.OPERATION_TYPE;
import com.model.PERIODICITY;
import com.model.WALLET;
import com.model.WALLET_OPERATION_TYPE_MAP;
import com.repository.*;
import com.service.*;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletOperationTypeMapServiceimp implements WalletOperationTypeMapService {
	 
	    
	    @Autowired
	    private WalletOperationTypeMapRepository repository;

	    @Autowired
	    private PeriodicityRepository periodicityRepository;

	    @Autowired
	    private WalletRepository walletRepository;

	    @Autowired
	    private OperationTypeRepository operationTypeRepository;

	    @Autowired
	    private FeesRepository feesRepository;


	    @Override
	    public WALLET_OPERATION_TYPE_MAP create(WALLET_OPERATION_TYPE_MAP walletOperationTypeMap) {
	        
	        // Gérer PERIODICITY
	        if (walletOperationTypeMap.getPeriodicity() != null &&
	            walletOperationTypeMap.getPeriodicity().getPerCode() != null) {
	            Integer perCode = walletOperationTypeMap.getPeriodicity().getPerCode();
	            PERIODICITY periodicity = periodicityRepository.findById(perCode)
	                .orElseThrow(() -> new RuntimeException("Periodicity not found with id: " + perCode));
	            walletOperationTypeMap.setPeriodicity(periodicity);
	        }

	        // Gérer WALLET
	        if (walletOperationTypeMap.getWallet() != null &&
	            walletOperationTypeMap.getWallet().getWalCode() != null) {
	            Integer walCode = walletOperationTypeMap.getWallet().getWalCode();
	            WALLET wallet = walletRepository.findById(walCode)
	                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walCode));
	            walletOperationTypeMap.setWallet(wallet);
	        }

	        // Gérer OPERATION_TYPE
	        if (walletOperationTypeMap.getOperationType() != null &&
	            walletOperationTypeMap.getOperationType().getOptCode() != null) {
	            Integer optCode = walletOperationTypeMap.getOperationType().getOptCode();
	            OPERATION_TYPE operationType = operationTypeRepository.findById(optCode)
	                .orElseThrow(() -> new RuntimeException("OperationType not found with id: " + optCode));
	            walletOperationTypeMap.setOperationType(operationType);
	        }

	        // Gérer FEES
	        if (walletOperationTypeMap.getFees() != null &&
	            walletOperationTypeMap.getFees().getFeeCode() != null) {
	            Integer feeCode = walletOperationTypeMap.getFees().getFeeCode();
	            FEES fees = feesRepository.findById(feeCode)
	                .orElseThrow(() -> new RuntimeException("Fees not found with id: " + feeCode));
	            walletOperationTypeMap.setFees(fees);
	        }

	        return repository.save(walletOperationTypeMap);
	    }

	    @Override
	    public WALLET_OPERATION_TYPE_MAP update(Integer id, WALLET_OPERATION_TYPE_MAP walletOperationTypeMap) {
	        Optional<WALLET_OPERATION_TYPE_MAP> existing = repository.findById(id);
	        if (existing.isPresent()) {
	            WALLET_OPERATION_TYPE_MAP updated = existing.get();
	            updated.setWotmLimitMax(walletOperationTypeMap.getWotmLimitMax());
	            updated.setWotmDispAmount(walletOperationTypeMap.getWotmDispAmount());
	            updated.setFees(walletOperationTypeMap.getFees());
	            updated.setPeriodicity(walletOperationTypeMap.getPeriodicity());
	            return repository.save(updated);
	        }
	        return null;
	    }

	    @Override
	    public void delete(Integer id) {
	        repository.deleteById(id);
	    }

	    @Override
	    public WALLET_OPERATION_TYPE_MAP getById(Integer id) {
	        return repository.findById(id).orElse(null);
	    }

	    @Override
	    public List<WALLET_OPERATION_TYPE_MAP> getAll() {
	        return repository.findAll();
	    }

        @Override
	    public List<WALLET_OPERATION_TYPE_MAP> searchWalletOperationTypeMaps(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchWalletOperationTypeMaps(searchWord);
	    }
}
