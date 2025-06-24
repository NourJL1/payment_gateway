package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CITY;
import com.model.COUNTRY;

public interface CityRepository extends JpaRepository<CITY, Integer> {

	List<CITY> findByCountry(COUNTRY country);

 @Query("SELECT c FROM CITY c WHERE " +
	           "LOWER(c.ctyLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(c.ctyCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(c.ctyIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CITY> searchCities(@Param("searchWord") String searchWord);
}
