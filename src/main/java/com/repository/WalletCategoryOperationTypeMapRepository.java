package com.repository;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface WalletCategoryOperationTypeMapRepository extends JpaRepository<WALLET_CATEGORY_OPERATION_TYPE_MAP, Integer>{
	
	
}
