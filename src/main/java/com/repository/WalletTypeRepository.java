package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.WALLET_TYPE;

public interface WalletTypeRepository extends JpaRepository<WALLET_TYPE, Integer> {
}
