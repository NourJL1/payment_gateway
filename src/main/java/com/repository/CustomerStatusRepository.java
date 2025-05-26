package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.CUSTOMER_STATUS;

public interface CustomerStatusRepository extends JpaRepository<CUSTOMER_STATUS, Integer>  {
    CUSTOMER_STATUS findByCtsCode(Integer ctsCode);


}
