package com.servicesImp;
import com.model.CUSTOMER_DOC_LISTE;
import com.repository.CustomerDocListeRepository;
import com.service.CustomerDocListeService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service
public class CustmerDocListeServiceImp implements CustomerDocListeService {

	@Autowired
    private CustomerDocListeRepository customerDocListeRepository;

	@Value("${document.storage.path}")
    private String storageDir ;
    private String allCustomerDir, allMerchantDir, allInternDir;

    @PostConstruct
    public void init() {
        this.allCustomerDir = storageDir + File.separator + "Customers" + File.separator;
        this.allMerchantDir = storageDir + File.separator + "Merchants" + File.separator;
        this.allInternDir = storageDir + File.separator + "Internes" + File.separator;
    }

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
		//String cdlPath = storageDir + "/" + user.getRole()  + "/" + File.separator + user.getId() + File.separator;
		try {
			Files.createDirectory(Paths.get(customerDocListe.getCdlLabe()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerDocListeRepository.save(customerDocListe);
	}

	@Override
	public void deleteById(Integer id) {
		customerDocListeRepository.deleteById(id);
		
	}
  @Override
    public List<CUSTOMER_DOC_LISTE> searchCustomerDocListes(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return customerDocListeRepository.findAll();
        }
        return customerDocListeRepository.searchCustomerDocListes(searchWord);
    }

}
