package com.service;
import com.model.CUSTOMER_STATUS;
import java.util.List;
import java.util.Optional;
public interface CustomerStatusService {
	
    List<CUSTOMER_STATUS> findAll();
    Optional<CUSTOMER_STATUS> findById(Integer id);
    CUSTOMER_STATUS save(CUSTOMER_STATUS customerStatus);
    void deleteById(Integer id);

}
