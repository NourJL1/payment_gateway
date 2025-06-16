package com.repository;
import com.model.FEE_RULE;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface FeeRuleRepository extends JpaRepository <FEE_RULE, Integer>{
@Query("SELECT fr FROM FEE_RULE fr WHERE " +
		       "LOWER(fr.fruLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
		       "LOWER(fr.fruPrimaryWalletId) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
		       "LOWER(fr.fruSens) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
		       "CAST(fr.fruCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruPrimaryFeesId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruFeesWalletId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruTva.vatCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +  // mise à jour ici
		       "CAST(fr.fruTvaWalletId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruPrimaryAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruFeesAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
		       "CAST(fr.fruTvaAmount AS string) LIKE CONCAT('%', :searchWord, '%') ")
		List<FEE_RULE> searchFeeRules(@Param("searchWord") String searchWord);
}
