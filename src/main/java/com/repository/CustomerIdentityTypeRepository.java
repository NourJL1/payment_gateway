package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CUSTOMER_IDENTITY_TYPE;

public interface CustomerIdentityTypeRepository extends JpaRepository<CUSTOMER_IDENTITY_TYPE, Integer>  {
@Query("SELECT cit FROM CUSTOMER_IDENTITY_TYPE cit WHERE " +
	           "LOWER(cit.citIden) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(cit.citLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(cit.citCode AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CUSTOMER_IDENTITY_TYPE> searchCustomerIdentityTypes(@Param("searchWord") String searchWord);
}
