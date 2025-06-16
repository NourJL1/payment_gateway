package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.*;

public interface FeesRepository extends JpaRepository<FEES, Integer> {
@Query("SELECT f FROM FEES f WHERE " +
	           "LOWER(f.feeLabel) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(f.feePercentage) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(f.feeCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.feeIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.financialInstitutionId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.feeMinLimit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.feeAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.feeMaxLimit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(f.feeMaxAmount AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<FEES> searchFees(@Param("searchWord") String searchWord);
}
