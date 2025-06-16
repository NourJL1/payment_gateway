package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.CUSTOMER_STATUS;

public interface CustomerStatusRepository extends JpaRepository<CUSTOMER_STATUS, Integer>  {
    CUSTOMER_STATUS findByCtsCode(Integer ctsCode);
      @Query("SELECT cs FROM CUSTOMER_STATUS cs WHERE " +
            "LOWER(cs.ctsLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "CAST(cs.ctsCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(cs.ctsIden AS string) LIKE CONCAT('%', :searchWord, '%')")
     List<CUSTOMER_STATUS> searchCustomerStatuses(@Param("searchWord") String searchWord);


}



