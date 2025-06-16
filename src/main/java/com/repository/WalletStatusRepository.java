package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WALLET_STATUS;

public interface WalletStatusRepository extends JpaRepository<WALLET_STATUS, Integer> {
@Query("SELECT ws FROM WALLET_STATUS ws WHERE " +
	           "LOWER(ws.wstLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(ws.wstCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(ws.wstIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<WALLET_STATUS> searchWalletStatuses(@Param("searchWord") String searchWord);
}