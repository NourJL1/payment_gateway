package com.servicesImp;
import com.model.CUSTOMER_IDENTITY_TYPE;
import com.repository.CustomerIdentityTypeRepository;
import com.service.CustomerIdentityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class CustomerIdentityTypeServiceImp  implements CustomerIdentityTypeService {

    @Autowired
    private CustomerIdentityTypeRepository customerIdentityTypeRepository;

	@Override
	public List<CUSTOMER_IDENTITY_TYPE> findAll() {
		return customerIdentityTypeRepository.findAll();
	}

	@Override
	public Optional<CUSTOMER_IDENTITY_TYPE> findById(Integer id) {
		 return customerIdentityTypeRepository.findById(id);
	}

	@Override
	public CUSTOMER_IDENTITY_TYPE save(CUSTOMER_IDENTITY_TYPE customerIdentityType) {
		 return customerIdentityTypeRepository.save(customerIdentityType);
	}

	@Override
	public void deleteById(Integer id) {
		  customerIdentityTypeRepository.deleteById(id);
		
	}
	

}
