package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CARD_TYPE;

public interface CardTypeRepository extends JpaRepository<CARD_TYPE, Integer>{
@Query("SELECT ct FROM CARD_TYPE ct WHERE " +
	           "LOWER(ct.ctypLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(ct.ctypCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(ct.ctypIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CARD_TYPE> searchCardTypes(@Param("searchWord") String searchWord);
}
