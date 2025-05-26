package com.service;
import com.model.FEE_RULE;
import java.util.List;
import java.util.Optional;
public interface FeeRuleService {
	 List<FEE_RULE> findAll();
	    Optional<FEE_RULE> findById(Integer id);
	    FEE_RULE save(FEE_RULE feeRule);
	    void deleteById(Integer id);

}
