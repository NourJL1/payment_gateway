package com.servicesImp;

import com.model.CITY;
import com.model.COUNTRY;
import com.model.CUSTOMER;
import com.model.CUSTOMER_STATUS;
import com.model.WALLET;
import com.repository.CityRepository;
import com.repository.CountryRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerService;
import com.service.TOTPService;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;


    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);

    @Override
    @Transactional
    public CUSTOMER createCustomer(CUSTOMER customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Le client ne peut pas être nul !");
        }
        if (customer.getStatus() != null) {
            Integer statusId;
            try {
                statusId = Integer.parseInt(customer.getStatus().getCtsIden());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Le statut du client doit être un entier valide !");
            }
            CUSTOMER_STATUS existingStatus = customerStatusRepository.findById(statusId)
                    .orElseThrow(() -> new IllegalStateException("Statut client introuvable !"));
            customer.setStatus(existingStatus);
        } else {
            throw new IllegalArgumentException("Le statut du client est obligatoire !");
        }
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
            customer.setCusIden(customerDetails.getCusIden());
            customer.setCountry(customerDetails.getCountry());
            customer.setCity(customerDetails.getCity());

            if (customer.getStatus().getCtsLabe().equals("ACTIVE") && customer.getWallet() == null)
                customer.setWallet(
                        new WALLET(null, null, null, null, 0f, 0f, 0f, null, null, customer, null, null, null,
                                null, null, null, null, null, null));
            // Do not update createdAt
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
    public List<CUSTOMER> getCustomersByStatus(Integer statusCode) {
        CUSTOMER_STATUS status = customerStatusRepository.findById(statusCode)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        return customerRepository.findByStatus(status);
    }

    @Override
    public List<CUSTOMER> getCustomersByCity(Integer cityCode) {
        CITY city = cityRepository.findById(cityCode)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return customerRepository.findByCity(city);
    }

    @Override
    public List<CUSTOMER> getCustomersByCountry(Integer countryCode) {
        COUNTRY country = countryRepository.findById(countryCode)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return customerRepository.findByCountry(country);
    }

    @Override
    public List<CUSTOMER> searchCustomers(String searchWord) {
        logger.debug("Searching customers with searchWord: {}", searchWord);
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return customerRepository.findAll();
        }
        return customerRepository.searchCustomers(searchWord);
    }

    @Override
    public long getTotalCustomerCount() {
        return customerRepository.count();
    }

    @Override
    public long getActiveCustomerCount(Integer statusCode) {
        CUSTOMER_STATUS status = customerStatusRepository.findById(statusCode)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        return customerRepository.countByStatus(status);
    }

    @Override
    public long getNewCustomersToday() {
        return customerRepository.countCustomersCreatedToday();
    }

    
}