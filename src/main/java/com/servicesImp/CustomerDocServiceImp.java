package com.servicesImp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CUSTOMER_DOC;
import com.repository.CustomerDocRepository;
import com.service.CustomerDocService;

@Service

public class CustomerDocServiceImp implements CustomerDocService {

    @Autowired
    private CustomerDocRepository customerDocRepository;

	@Override
	public List<CUSTOMER_DOC> findAll() {
		 return customerDocRepository.findAll();
	}

	@Override
	public Optional<CUSTOMER_DOC> findById(Integer id) {
		 return customerDocRepository.findById(id);
	}

	@Override
	public CUSTOMER_DOC save(CUSTOMER_DOC customerDoc) {
		 return customerDocRepository.save(customerDoc);
	}

	@Override
	public void deleteById(Integer id) {
		 customerDocRepository.deleteById(id);
		
	}

}
