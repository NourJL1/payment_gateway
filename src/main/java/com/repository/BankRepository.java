package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.BANK;

public interface BankRepository extends JpaRepository<BANK, Integer> {
@Query("SELECT b FROM BANK b WHERE " +
	           "LOWER(b.banCorpName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(b.banInit) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(b.banCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(b.banIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(b.banFinId AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<BANK> searchBanks(@Param("searchWord") String searchWord);
}
