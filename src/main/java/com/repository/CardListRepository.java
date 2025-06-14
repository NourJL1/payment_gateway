package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CARD_LIST;
public interface CardListRepository extends JpaRepository<CARD_LIST, Integer> {

@Query("SELECT cl FROM CARD_LIST cl WHERE " +
	           "LOWER(cl.cliLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(cl.cliCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(cl.cliIden AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<CARD_LIST> searchCardLists(@Param("searchWord") String searchWord);
}