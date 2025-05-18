package com.servicesImp;
import com.model.CITY;
import com.model.COUNTRY;
import com.model.CUSTOMER;
import com.model.CUSTOMER_STATUS;
import com.repository.CityRepository;
import com.repository.CountryRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImp implements CustomerService {
	 @Autowired
	    private CustomerRepository customerRepository;
	 @Autowired
	 private CityRepository cityRepository;
	 @Autowired
	 private CountryRepository countryRepository;
	 
	 @Autowired
	 private CustomerStatusRepository customerStatusRepository;



	 @Override
	 @Transactional
	 public CUSTOMER createCustomer(CUSTOMER customer) {
	     if (customer == null) {
	         throw new IllegalArgumentException("Le client ne peut pas être nul !");
	     }

	     // Gestion de la ville (CITY)
	     if (customer.getCity() != null) {
	         CITY existingCity = cityRepository.findById(customer.getCity().getCtyCode())
	                                           .orElseGet(() -> cityRepository.save(customer.getCity()));
	         customer.setCity(existingCity);
	     }

	     // Gestion du pays (COUNTRY)
	     if (customer.getCountry() != null) {
	         COUNTRY existingCountry = countryRepository.findById(customer.getCountry().getCtrCode())
	                                                    .orElseGet(() -> countryRepository.save(customer.getCountry()));
	         customer.setCountry(existingCountry);
	     }

	     // Gestion du statut du client (CUSTOMER_STATUS)
	     if (customer.getStatus() != null) {
	         CUSTOMER_STATUS existingStatus = customerStatusRepository.findById(customer.getStatus().getCtsIden())
	                                                                  .orElseThrow(() -> new IllegalStateException("Statut client introuvable !"));
	         customer.setStatus(existingStatus);
	     } else {
	         throw new IllegalArgumentException("Le statut du client est obligatoire !");
	     }

	     // Sauvegarde du customer avec les entités bien gérées
	     return customerRepository.save(customer);
	 }


	@Override
	public Optional<CUSTOMER> getCustomerById(Integer cusCode) {
        return customerRepository.findById(cusCode);

	}

	@Override
	public List<CUSTOMER> getAllCustomers() {
        return customerRepository.findAll();

	}

	@Override
	public CUSTOMER updateCustomer(Integer cusCode, CUSTOMER customerDetails) {
		return customerRepository.findById(cusCode).map(customer -> {
            customer.setCusFirstName(customerDetails.getCusFirstName());
            customer.setCusMidName(customerDetails.getCusMidName());
            customer.setCusLastName(customerDetails.getCusLastName());
            customer.setCusMailAddress(customerDetails.getCusMailAddress());
            customer.setCusMotDePasse(customerDetails.getCusMotDePasse());
            customer.setCusPhoneNbr(customerDetails.getCusPhoneNbr());
            customer.setCusAddress(customerDetails.getCusAddress());
            customer.setStatus(customerDetails.getStatus());
            customer.setIdentity(customerDetails.getIdentity());
            customer.setCity(customerDetails.getCity());
            customer.setCusIden(customerDetails.getCusIden());

            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer non trouvé"));
    }
	

	@Override
	public void deleteCustomer(Integer cusCode) {
		if (customerRepository.existsById(cusCode)) {
            customerRepository.deleteById(cusCode);
        } else {
            throw new RuntimeException("Customer non trouvé");
        }		
	}

	@Override
	public boolean existsById(Integer cusCode) {
        return customerRepository.existsById(cusCode);

	}

	@Override
	public Optional<CUSTOMER> getCustomerByEmail(String email) {
        return customerRepository.findByCusMailAddress(email);

	}

	@Override
	public Optional<CUSTOMER> getCustomerByPhone(String phone) {
        return customerRepository.findByCusPhoneNbr(phone);

	}

	@Override
	public List<CUSTOMER> getCustomersByCity(Integer cityCode) {
        return customerRepository.findByCity_CtyCode(cityCode);

	}

	@Override
	public List<CUSTOMER> getCustomersByCountry(Integer countryCode) {
        return customerRepository.findByCity_CtyCode(countryCode);

	}

	@Override
	public List<CUSTOMER> getCustomersWithWallets() {
        return customerRepository.findByWalletsIsNotEmpty();

	}

	@Override
	public List<CUSTOMER> getCustomersWithoutWallets() {
        return customerRepository.findByWalletsIsEmpty();

	}

	@Override
	public List<CUSTOMER> searchCustomers(String name, String email, String phone, Integer cityCode,
			Integer countryCode) {
        return customerRepository.searchCustomers(name, email, phone, cityCode, countryCode);

	}
	
	

}
