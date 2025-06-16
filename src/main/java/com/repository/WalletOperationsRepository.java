package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WALLET_OPERATIONS;

public interface WalletOperationsRepository extends JpaRepository<WALLET_OPERATIONS, Integer>{
	 @Query("SELECT wo FROM WALLET_OPERATIONS wo WHERE " +
	           "LOWER(wo.wopCurrency) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(wo.wopStatus) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(wo.wopLabel) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(wo.wopCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wo.wopIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wo.wopOtyCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wo.wopAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wo.wopTimestamps AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<WALLET_OPERATIONS> searchWalletOperations(@Param("searchWord") String searchWord);
	}
