package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CUSTOMER_DOC_LISTE;
public interface CustomerDocListeRepository extends JpaRepository<CUSTOMER_DOC_LISTE, Integer>{

@Query("SELECT cdl FROM CUSTOMER_DOC_LISTE cdl WHERE " +
	           "LOWER(cdl.cdlLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(cdl.cdlCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cdl.cdlIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CUSTOMER_DOC_LISTE> searchCustomerDocListes(@Param("searchWord") String searchWord);
}
