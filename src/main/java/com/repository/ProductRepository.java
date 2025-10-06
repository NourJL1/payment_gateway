package com.repository;

import com.model.PRODUCT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<PRODUCT, Integer> {
    Optional<PRODUCT> findByProIden(String proIden);
    List<PRODUCT> findByProIsActiveTrue();
    List<PRODUCT> findByWalletWalCode(Integer walCode);
    
    @Query("SELECT p FROM PRODUCT p WHERE p.proIsActive = true AND " +
           "(LOWER(p.proName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.proDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<PRODUCT> searchActiveProducts(@Param("searchTerm") String searchTerm);
}