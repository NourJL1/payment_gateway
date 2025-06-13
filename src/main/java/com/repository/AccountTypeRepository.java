package com.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.ACCOUNT_TYPE;

public interface AccountTypeRepository extends JpaRepository<ACCOUNT_TYPE, Integer> {

@Query("SELECT at FROM ACCOUNT_TYPE at WHERE " +
	           "LOWER(at.atyLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(at.atyCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(at.atyIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(at.atyFinId AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<ACCOUNT_TYPE> searchAccountTypes(@Param("searchWord") String searchWord);
}
