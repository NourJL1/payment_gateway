package com.repository;
import com.model.CUSTOMER_CONTACTS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerContactsRepository extends JpaRepository<CUSTOMER_CONTACTS, Integer> {
 @Query("SELECT cc FROM CUSTOMER_CONTACTS cc WHERE " +
	           "LOWER(cc.ccoContactName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(cc.ccoContactMail) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(cc.ccoContactPhone) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(cc.ccoCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cc.ccoIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cc.ccoAddedAt AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cc.ccoLastInteraction AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CUSTOMER_CONTACTS> searchCustomerContacts(@Param("searchWord") String searchWord);
}
