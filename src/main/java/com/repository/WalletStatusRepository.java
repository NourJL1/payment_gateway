package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.WALLET_STATUS;

public interface WalletStatusRepository extends JpaRepository<WALLET_STATUS, Integer> {

}
