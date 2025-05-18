package com.servicesImp;
import com.model.CUSTOMER_STATUS;
import com.repository.CustomerStatusRepository;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerStatusServiceImp implements CustomerStatusService {

	@Autowired
    private CustomerStatusRepository customerStatusRepository;

	@Override
	public List<CUSTOMER_STATUS> findAll() {
		 return customerStatusRepository.findAll();	}

	@Override
	public Optional<CUSTOMER_STATUS> findById(Integer id) {
		return customerStatusRepository.findById(id);
	}

	@Override
	public CUSTOMER_STATUS save(CUSTOMER_STATUS customerStatus) {
		 return customerStatusRepository.save(customerStatus);
	}

	@Override
	public void deleteById(Integer id) {
		  customerStatusRepository.deleteById(id);
		
	}
}
