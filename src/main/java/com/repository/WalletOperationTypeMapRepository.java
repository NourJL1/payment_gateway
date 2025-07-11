package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.*;
public interface WalletOperationTypeMapRepository extends JpaRepository<WALLET_OPERATION_TYPE_MAP, Integer>{

@Query("SELECT wotm FROM WALLET_OPERATION_TYPE_MAP wotm WHERE " +
	          
	           "CAST(wotm.wotmCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           
	           "CAST(wotm.wotmLimitMax AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(wotm.wotmDispAmount AS string) LIKE CONCAT('%', :searchWord, '%')")
	    List<WALLET_OPERATION_TYPE_MAP> searchWalletOperationTypeMaps(@Param("searchWord") String searchWord);
}
