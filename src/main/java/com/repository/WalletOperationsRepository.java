package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.WALLET_OPERATIONS;

public interface WalletOperationsRepository extends JpaRepository<WALLET_OPERATIONS, Integer>{
	
	}
