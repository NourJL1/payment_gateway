package com.servicesImp;
import com.model.CUSTOMER_DOC_LISTE;
import com.repository.CustomerDocListeRepository;
import com.service.CustomerDocListeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustmerDocListeServiceImp implements CustomerDocListeService {

	@Autowired
    private CustomerDocListeRepository customerDocListeRepository;

	@Override
	public List<CUSTOMER_DOC_LISTE> findAll() {
        return customerDocListeRepository.findAll();

	}

	@Override
	public Optional<CUSTOMER_DOC_LISTE> findById(Integer id) {
		 return customerDocListeRepository.findById(id);
	}

	@Override
	public CUSTOMER_DOC_LISTE save(CUSTOMER_DOC_LISTE customerDocListe) {
		return customerDocListeRepository.save(customerDocListe);
	}

	@Override
	public void deleteById(Integer id) {
		customerDocListeRepository.deleteById(id);
		
	}

}
