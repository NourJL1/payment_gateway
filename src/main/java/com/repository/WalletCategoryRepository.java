package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WALLET_CATEGORY;

public interface WalletCategoryRepository extends JpaRepository<WALLET_CATEGORY, Integer> {

 @Query("SELECT wc FROM WALLET_CATEGORY wc WHERE " +
	           "LOWER(wc.wcaLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(wc.wcaCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wc.wcaIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wc.wcaFinId AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<WALLET_CATEGORY> searchWalletCategories(@Param("searchWord") String searchWord);
}
