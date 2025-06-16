package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.DOC_TYPE;
public interface DocTypeRepository extends JpaRepository<DOC_TYPE, Integer> {

@Query("SELECT dt FROM DOC_TYPE dt WHERE " +
	           "LOWER(dt.dtyLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(dt.dtyCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(dt.dtyIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<DOC_TYPE> searchDocTypes(@Param("searchWord") String searchWord);
}
