package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.COUNTRY;

public interface CountryRepository extends JpaRepository<COUNTRY, Integer>{
	
	public boolean existsByCtrLabe (String ctrLabe);
@Query("SELECT c FROM COUNTRY c WHERE " +
	           "LOWER(c.ctrLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(c.ctrCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(c.ctrIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<COUNTRY> searchCountries(@Param("searchWord") String searchWord);
}
