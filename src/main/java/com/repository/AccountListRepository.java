package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.ACCOUNT_LIST;

public interface AccountListRepository extends JpaRepository<ACCOUNT_LIST, Integer> {
    List<ACCOUNT_LIST> findByWallet_WalCode(Integer walletCode);

}
