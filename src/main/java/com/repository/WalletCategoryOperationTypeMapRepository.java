package com.repository;
import com.model.OPERATION_TYPE;
import com.model.WALLET_CATEGORY;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletCategoryOperationTypeMapRepository extends JpaRepository<WALLET_CATEGORY_OPERATION_TYPE_MAP, Integer> {
  Optional<WALLET_CATEGORY_OPERATION_TYPE_MAP> findByOperationTypeAndWalletCategory(
			OPERATION_TYPE operationType,
			WALLET_CATEGORY walletCategory);  
  @Query("SELECT wcotm FROM WALLET_CATEGORY_OPERATION_TYPE_MAP wcotm " +
           "LEFT JOIN wcotm.operationType ot " +
           "LEFT JOIN wcotm.walletCategory wc " +
           "INNER JOIN wcotm.fees f " +
           "INNER JOIN wcotm.periodicity p " +
           "WHERE LOWER(CAST(COALESCE(wcotm.id, 0) AS string)) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(CAST(COALESCE(wcotm.limitMax, 0) AS string)) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(CAST(COALESCE(wcotm.financialInstitutionId, 0) AS string)) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(COALESCE(ot.optLabe, '')) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(COALESCE(wc.wcaLabe, '')) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(COALESCE(f.feeLabel, '')) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
           "LOWER(COALESCE(p.perLabe, '')) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<WALLET_CATEGORY_OPERATION_TYPE_MAP> searchWalletCategoryOperationTypeMaps(@Param("searchWord") String searchWord);
}