package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.model.OPERATION_DETAILS;
import com.model.WALLET_OPERATIONS;

public interface OperationsDetailsRepository extends JpaRepository<OPERATION_DETAILS, Integer> {
}




