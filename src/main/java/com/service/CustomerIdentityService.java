package com.service;
import com.model.CUSTOMER_IDENTITY;
import java.util.List;
import java.util.Optional;
public interface CustomerIdentityService {
	List<CUSTOMER_IDENTITY> findAll();
    Optional<CUSTOMER_IDENTITY> findById(Integer id);
    CUSTOMER_IDENTITY save(CUSTOMER_IDENTITY customerIdentity);
    void deleteById(Integer id);

}
