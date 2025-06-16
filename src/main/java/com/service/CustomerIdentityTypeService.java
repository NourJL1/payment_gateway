package com.service;
import com.model.CUSTOMER_IDENTITY_TYPE;
import java.util.List;
import java.util.Optional;
public interface CustomerIdentityTypeService {
	
	 List<CUSTOMER_IDENTITY_TYPE> findAll();
	    Optional<CUSTOMER_IDENTITY_TYPE> findById(Integer id);
	    CUSTOMER_IDENTITY_TYPE save(CUSTOMER_IDENTITY_TYPE customerIdentityType);
	    void deleteById(Integer id);
        List<CUSTOMER_IDENTITY_TYPE> searchCustomerIdentityTypes(String searchWord);

}
