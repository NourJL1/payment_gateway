package com.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.*;
public interface WalletOperationTypeMapRepository extends JpaRepository<WALLET_OPERATION_TYPE_MAP, Integer>{
Optional<WALLET_OPERATION_TYPE_MAP> findByOperationTypeAndWallet(OPERATION_TYPE operationType, WALLET wallet);
@Query("SELECT wotm FROM WALLET_OPERATION_TYPE_MAP wotm WHERE " +
	           "CAST(wotm.wotmCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmFinId AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmLimitMax AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmDispAmount AS string) LIKE CONCAT('%', :searchWord, '%')"/*  OR " +
	           "CAST(wotm.wotmFeeIden AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "LOWER(wotm.wotmFeeLab) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(wotm.wotmFeePercentage) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "CAST(wotm.wotmFeeMinLimit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmFeeAmount AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmFeeMaxLimit AS string) LIKE CONCAT('%', :searchWord, '%')" */)
	    List<WALLET_OPERATION_TYPE_MAP> searchWalletOperationTypeMaps(@Param("searchWord") String searchWord);
}
