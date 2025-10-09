package com.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.ACCOUNT;
public interface AccountRepository extends JpaRepository<ACCOUNT, Integer> {
	
//	public Optional<ACCOUNT_TYPE> findById(Integer atyFinId);
 // Add this method for duplicate checking
    Optional<ACCOUNT> findByAccRib(String accRib);
    
List<ACCOUNT> findByAccountList_AliCode(Integer aliCode);

	@Query("SELECT a FROM ACCOUNT a WHERE " +
	           "LOWER(a.accRib) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(a.accKey) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(a.accCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(a.accIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<ACCOUNT> searchAccounts(@Param("searchWord") String searchWord);

	
	

}
