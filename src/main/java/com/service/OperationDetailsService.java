package com.service;

import java.util.List;
import java.util.Optional;
import com.model.*;

public interface OperationDetailsService {
    List<OPERATION_DETAILS> findAll();
    Optional<OPERATION_DETAILS> findById(Integer id);
    OPERATION_DETAILS save(OPERATION_DETAILS operationDetails);
    void deleteById(Integer id);
    List<OPERATION_DETAILS> searchOperationDetails(String searchWord);

    List<OPERATION_DETAILS> findRecentTransactionsByCustomerAndWallet(Integer cusCode, String walIden, Integer hours);
    List<OPERATION_DETAILS> findByWalletIden(String walIden);
    
}
