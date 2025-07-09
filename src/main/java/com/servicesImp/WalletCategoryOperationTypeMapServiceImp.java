package com.servicesImp;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;
import com.repository.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class WalletCategoryOperationTypeMapServiceImp implements WalletCategoryOperationTypeMapService  {
	 @Autowired
	    private WalletCategoryOperationTypeMapRepository repository;

	    @Override
	    public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getAll() {
	        return repository.findAll();
	    }

	    @Override
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP getById(Integer id) {
	        return repository.findById(id).orElse(null);
	    }

	    @Override
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP create(WALLET_CATEGORY_OPERATION_TYPE_MAP mapping) {
	        return repository.save(mapping);
	    }

	    @Override
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP update(Integer id, WALLET_CATEGORY_OPERATION_TYPE_MAP mapping) {
	        return repository.findById(id).map(existing -> {
	            existing.setLimitMax(mapping.getLimitMax());
	            existing.setFeeIden(mapping.getFeeIden());
	            existing.setFeeLabel(mapping.getFeeLabel());
	            existing.setFeeMinLimit(mapping.getFeeMinLimit());
	            existing.setFeeAmount(mapping.getFeeAmount());
	            existing.setFeeMaxLimit(mapping.getFeeMaxLimit());
	            existing.setFeePercentage(mapping.getFeePercentage());
	            existing.setFeeMaxAmount(mapping.getFeeMaxAmount());
	            existing.setFinancialInstitutionId(mapping.getFinancialInstitutionId());
	            return repository.save(existing);
	        }).orElse(null);
	    }

	    @Override
	    public void delete(Integer id) {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	        } else {
	            throw new RuntimeException("Mapping non trouvé avec ID: " + id);
	        }
	    }

      @Override
	    public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> searchWalletCategoryOperationTypeMaps(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchWalletCategoryOperationTypeMaps(searchWord);
	    }

	    }


