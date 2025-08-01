package com.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.OPERATION_TYPE;
public interface OperationTypeRepository extends JpaRepository<OPERATION_TYPE, Integer> {
    List<OPERATION_TYPE> findByWallet_WalCode(Integer walCode);
    Optional<OPERATION_TYPE> findByOptIden(String optIden);
  @Query("SELECT ot FROM OPERATION_TYPE ot WHERE " +
            "LOWER(ot.optLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(ot.optFscLab) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "CAST(ot.optCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(ot.optIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(ot.optFscIden AS string) LIKE CONCAT('%', :searchWord, '%')")
     List<OPERATION_TYPE> searchOperationTypes(@Param("searchWord") String searchWord);
}
