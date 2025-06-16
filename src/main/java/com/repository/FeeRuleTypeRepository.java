package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.FEE_RULE_TYPE;

public interface FeeRuleTypeRepository extends JpaRepository<FEE_RULE_TYPE, Integer> {

@Query("SELECT frt FROM FEE_RULE_TYPE frt WHERE " +
	           "LOWER(frt.frtLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(frt.frtCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(frt.frtIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<FEE_RULE_TYPE> searchFeeRuleTypes(@Param("searchWord") String searchWord);
}
