package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;
import com.model.OPERATION_DETAILS;

public interface OperationsDetailsRepository extends JpaRepository<OPERATION_DETAILS, Integer> {

    @Query("SELECT od FROM OPERATION_DETAILS od WHERE " +
            "LOWER(od.odeType) LIKE LOWER(CONCAT('%', :searchWord, '%')) " +
            "OR LOWER(od.odeValue) LIKE LOWER(CONCAT('%', :searchWord, '%')) " +
            "OR LOWER(od.odePayMeth) LIKE LOWER(CONCAT('%', :searchWord, '%')) " +
            "OR LOWER(od.odeRecipientWallet) LIKE LOWER(CONCAT('%', :searchWord, '%')) " +
            "OR CAST(od.odeCode AS string) LIKE CONCAT('%', :searchWord, '%') " +
            "OR CAST(od.odeCusCode AS string) LIKE CONCAT('%', :searchWord, '%') " +
            "OR CAST(od.odeFeeAmount AS string) LIKE CONCAT('%', :searchWord, '%') " +
            "OR CAST(od.odeIden AS string) LIKE CONCAT('%', :searchWord, '%') " +
            "OR CAST(od.odeCreatedAt AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<OPERATION_DETAILS> searchOperationDetails(@Param("searchWord") String searchWord);

    @Query("SELECT od FROM OPERATION_DETAILS od " +
           "JOIN od.walletOperation wo " +
           "JOIN wo.wallet w " +
           "WHERE od.odeCusCode = :cusCode " +
           "AND w.walIden = :walIden " +
           "AND od.odeCreatedAt >= :cutoffDate " +
           "ORDER BY od.odeCreatedAt DESC")
    List<OPERATION_DETAILS> findByCustomerCodeAndWalletIdenAndDateAfter(
            @Param("cusCode") Integer cusCode,
            @Param("walIden") String walIden,
            @Param("cutoffDate") Date cutoffDate
    );

    @Query("SELECT od FROM OPERATION_DETAILS od " +
           "JOIN od.walletOperation wo " +
           "JOIN wo.wallet w " +
           "WHERE w.walIden = :walIden " +
           "ORDER BY od.odeCreatedAt DESC")
    List<OPERATION_DETAILS> findByWalletIden(@Param("walIden") String walIden);

    
}
