package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.*;
public interface FeeSchemaRepository extends JpaRepository<FEE_SCHEMA, Integer> {
  @Query("SELECT fs FROM FEE_SCHEMA fs WHERE " +
	           "LOWER(fs.fscLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(fs.fscCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(fs.fscIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<FEE_SCHEMA> searchFeeSchemas(@Param("searchWord") String searchWord);
}

