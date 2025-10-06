package com.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CARD;

public interface CardRepository extends JpaRepository<CARD, Integer> {

  // Add this method for duplicate checking
    Optional<CARD> findByCarNumb(String carNumb);
@Query("SELECT c FROM CARD c WHERE " +
	           "LOWER(c.carNumb) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(c.carEmvData) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(c.carLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(c.carCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(c.carIden AS string) LIKE CONCAT('%', :searchWord, '%') OR "+
	           "CAST(c.carExpiryDate AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CARD> searchCards(@Param("searchWord") String searchWord);
}
