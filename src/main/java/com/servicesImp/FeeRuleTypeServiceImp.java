package com.servicesImp;


import com.model.FEE_RULE_TYPE;
import com.repository.FeeRuleTypeRepository;
import com.service.FeeRuleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FeeRuleTypeServiceImp implements FeeRuleTypeService {

    @Autowired
    private FeeRuleTypeRepository feeRuleTypeRepository;

	@Override
	public List<FEE_RULE_TYPE> findAll() {
		return feeRuleTypeRepository.findAll();
	}

	@Override
	public Optional<FEE_RULE_TYPE> findById(Integer id) {
        return feeRuleTypeRepository.findById(id);

	}

	@Override
	public FEE_RULE_TYPE save(FEE_RULE_TYPE feeRuleType) {
        return feeRuleTypeRepository.save(feeRuleType);

	}

	@Override
	public void deleteById(Integer id) {
        feeRuleTypeRepository.deleteById(id);
		
	}

}
