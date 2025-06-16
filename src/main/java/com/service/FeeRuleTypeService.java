package com.service;
import com.model.FEE_RULE_TYPE;
import java.util.List;
import java.util.Optional;

public interface FeeRuleTypeService {
	List<FEE_RULE_TYPE> findAll();
    Optional<FEE_RULE_TYPE> findById(Integer id);
    FEE_RULE_TYPE save(FEE_RULE_TYPE feeRuleType);
    void deleteById(Integer id);
    List<FEE_RULE_TYPE> searchFeeRuleTypes(String searchWord);


}
