package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CUSTOMER_DOC;

public interface CustomerDocRepository extends JpaRepository<CUSTOMER_DOC, Integer>{
 @Query("SELECT cd FROM CUSTOMER_DOC cd WHERE " +
	           "LOWER(cd.cdoLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(cd.cdoCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cd.cdoIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CUSTOMER_DOC> searchCustomerDocs(@Param("searchWord") String searchWord);
}
