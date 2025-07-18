package com.servicesImp;
import com.model.CUSTOMER_IDENTITY;
import com.model.CUSTOMER_IDENTITY_TYPE;
import com.repository.CustomerIdentityRepository;
import com.repository.CustomerIdentityTypeRepository;
import com.service.CustomerIdentityService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerIdentityServiceImp implements CustomerIdentityService  {

    @Autowired
    private CustomerIdentityRepository repository;
    @Autowired
    private CustomerIdentityTypeRepository customerIdentityTypeRepository;
    
   
	@Override
	public List<CUSTOMER_IDENTITY> findAll() {
		 return repository.findAll();
	}

	@Override
	public Optional<CUSTOMER_IDENTITY> findById(Integer id) {
		return repository.findById(id);
	}

	@Transactional
	public CUSTOMER_IDENTITY save(CUSTOMER_IDENTITY customerIdentity) {
	    if (customerIdentity.getCustomerIdentityType() == null || 
	        customerIdentity.getCustomerIdentityType().getCitCode() == null) {
	        throw new IllegalArgumentException("Customer Identity Type must be specified before saving");
	    }

	    CUSTOMER_IDENTITY_TYPE identityType = customerIdentityTypeRepository.findById(
	            customerIdentity.getCustomerIdentityType().getCitCode())
	        .orElseThrow(() -> new IllegalArgumentException("Invalid Customer Identity Type"));

	    customerIdentity.setCustomerIdentityType(identityType);
	    
	    return repository.save(customerIdentity);
	}


	
	

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public boolean existsByCidNum(String cidNum) {
		return repository.existsByCidNum(cidNum);
	}

}
