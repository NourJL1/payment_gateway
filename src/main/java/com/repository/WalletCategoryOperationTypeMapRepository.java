package com.repository;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface WalletCategoryOperationTypeMapRepository extends JpaRepository<WALLET_CATEGORY_OPERATION_TYPE_MAP, Integer>{
	
@Query("SELECT wcotm FROM WALLET_CATEGORY_OPERATION_TYPE_MAP wcotm WHERE " +
	           "CAST(wcotm.id AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wcotm.limitMax AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wcotm.financialInstitutionId AS string) LIKE CONCAT('%', :searchWord, '%')"/* OR " +
	           "CAST(wcotm.feeIden AS string) LIKE CONCAT('%', :searchWord, '%')  OR " +
	           "LOWER(wcotm.feeLabel) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(wcotm.feePercentage) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(wcotm.feeMinLimit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wcotm.feeAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wcotm.feeMaxLimit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wcotm.feeMaxAmount AS string) LIKE CONCAT('%', :searchWord, '%')" */)
	    List<WALLET_CATEGORY_OPERATION_TYPE_MAP> searchWalletCategoryOperationTypeMaps(@Param("searchWord") String searchWord);   
}
