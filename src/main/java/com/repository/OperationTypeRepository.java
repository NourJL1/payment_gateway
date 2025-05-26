package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.OPERATION_TYPE;
public interface OperationTypeRepository extends JpaRepository<OPERATION_TYPE, Integer> {
    List<OPERATION_TYPE> findByWallet_WalCode(Integer walCode);

}
