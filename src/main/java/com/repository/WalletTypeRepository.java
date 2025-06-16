package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WALLET_TYPE;

public interface WalletTypeRepository extends JpaRepository<WALLET_TYPE, Integer> {
@Query("SELECT wt FROM WALLET_TYPE wt WHERE " +
	           "LOWER(wt.wtyLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(wt.wtyCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wt.wtyIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<WALLET_TYPE> searchWalletTypes(@Param("searchWord") String searchWord);
}