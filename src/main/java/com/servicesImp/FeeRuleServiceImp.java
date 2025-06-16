package com.servicesImp;
import com.repository.FeeRuleRepository;
import com.service.FeeRuleService;
import com.service.FeeRuleTypeService;
import com.service.FeeSchemaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.*;

import java.util.List;
import java.util.Optional;

@Service

public class FeeRuleServiceImp implements FeeRuleService {
	@Autowired
    private FeeRuleRepository repository;
	
	@Autowired 
	private FeeRuleTypeService feeRuleTypeService;
	
	@Autowired
	private FeeSchemaService feeSchemaService;

	@Override
	public List<FEE_RULE> findAll() {
		 return repository.findAll();
	}

	@Override
	public Optional<FEE_RULE> findById(Integer id) {
		 return repository.findById(id);
	}

	@Override
    public FEE_RULE save(FEE_RULE feeRule) {
        // Vérification de la présence de FeeRuleType et FeeSchema
        if (feeRule.getFeeRuleType() == null || feeRule.getFeeRuleType().getFrtCode() == null) {
            throw new IllegalArgumentException("FeeRuleType is required.");
        }

        if (feeRule.getFeeSchema() == null || feeRule.getFeeSchema().getFscCode() == null) {
            throw new IllegalArgumentException("FeeSchema is required.");
        }

        // Récupération des entités FeeRuleType et FeeSchema
        Optional<FEE_RULE_TYPE> feeRuleTypeOpt = feeRuleTypeService.findById(feeRule.getFeeRuleType().getFrtCode());
        Optional<FEE_SCHEMA> feeSchemaOpt = feeSchemaService.findById(feeRule.getFeeSchema().getFscCode());

        if (!feeRuleTypeOpt.isPresent()) {
            throw new IllegalArgumentException("FeeRuleType not found.");
        }

        if (!feeSchemaOpt.isPresent()) {
            throw new IllegalArgumentException("FeeSchema not found.");
        }

        // Affectation des entités trouvées à FeeRule
        feeRule.setFeeRuleType(feeRuleTypeOpt.get());
        feeRule.setFeeSchema(feeSchemaOpt.get());

        return repository.save(feeRule);
    }

	@Override
	public void deleteById(Integer id) {
		  repository.deleteById(id);
		
	}
   @Override
	    public List<FEE_RULE> searchFeeRules(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchFeeRules(searchWord);
	    }

    
}
