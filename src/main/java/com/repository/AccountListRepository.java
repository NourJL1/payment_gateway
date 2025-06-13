package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.ACCOUNT_LIST;

public interface AccountListRepository extends JpaRepository<ACCOUNT_LIST, Integer> {
    List<ACCOUNT_LIST> findByWallet_WalCode(Integer walletCode);
    @Query("SELECT al FROM ACCOUNT_LIST al WHERE " +
            "LOWER(al.aliLabe) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "CAST(al.aliCode AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(al.aliIden AS string) LIKE CONCAT('%', :searchWord, '%')")
     List<ACCOUNT_LIST> searchAccountLists(@Param("searchWord") String searchWord);

}
