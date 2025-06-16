package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.PERIODICITY;

public interface PeriodicityRepository extends JpaRepository<PERIODICITY, Integer> {
@Query("SELECT p FROM PERIODICITY p WHERE " +
	           "LOWER(p.perLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(p.perCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(p.perIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<PERIODICITY> searchPeriodicities(@Param("searchWord") String searchWord);
}
