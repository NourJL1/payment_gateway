package com.controller;

import com.repository.CityRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerDocListeService;
import com.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.*;

import jakarta.validation.Valid;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Autowired
    private CustomerDocListeService customerDocListeService;

    /* @Autowired
    private AuthenticationManager authenticationManager; */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CUSTOMER customer) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Creating customer: " + mapper.writeValueAsString(customer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (customer.getCity() == null || customer.getCity().getCtyCode() == null) {
            return ResponseEntity.badRequest().body("City information is required.");
        }
        cityRepository.findById(customer.getCity().getCtyCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "City not found"));
        if (customerRepository.existsByCusMailAddress(customer.getCusMailAddress())) {
            return ResponseEntity.badRequest().body("email already in use");
        }
        if (customer.getStatus() == null)
            customer.setStatus(customerStatusRepository.findByCtsCode(3));

        if (customer.getStatus().getCtsLabe().equals("ACTIVE") && customer.getWallet() == null)
            customer.setWallet(
                    new WALLET(null, null, null, null, 0f, 0f, 0f, null, null, customer, null, null, null,
                            null, null, null, null, null, null));
        System.out.println(customer.getIdentity());
                            
        customerDocListeService.save(customer.getIdentity().getCustomerDocListe());
        customer.setCusCode(null);
        customer.setCusMotDePasse(passwordEncoder.encode(customer.getCusMotDePasse()));
        customer.setMfaEnabled(false);
        CUSTOMER savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER> getCustomerById(@PathVariable Integer id) {
        System.out.println("Fetching customer with ID: " + id);
        Optional<CUSTOMER> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("existsByEmail/{cusMailAddress}")
    public boolean existsByEmail(@PathVariable String cusMailAddress) {
        return customerRepository.existsByCusMailAddress(cusMailAddress);
    }

    @GetMapping("existsByUsername/{username}")
    public boolean existsByUsername(@PathVariable String username) {
        return customerRepository.existsByUsername(username);
    }

    @GetMapping("existsByCusPhoneNbr/{phone}")
    public boolean existsByCusPhoneNbr(@PathVariable String phone) {
        return customerRepository.existsByCusPhoneNbr(phone);
    }

    @GetMapping
    public ResponseEntity<List<CUSTOMER>> getAllCustomers() {
        List<CUSTOMER> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalCustomerCount() {
        long count = customerService.getTotalCustomerCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/active/{statusCode}")
    public ResponseEntity<Long> getActiveCustomerCount(@PathVariable Integer statusCode) {
        long count = customerService.getActiveCustomerCount(statusCode);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/new-today")
    public ResponseEntity<Long> getNewCustomersTodayCount() {
        long count = customerService.getNewCustomersTodayCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/new-today")
    public ResponseEntity<List<CUSTOMER>> getNewCustomersToday() {
        return ResponseEntity.ok(customerService.getNewCustomersToday());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CUSTOMER> updateCustomer(@PathVariable Integer id, @RequestBody CUSTOMER customerDetails) {
        try {
            CUSTOMER updatedCustomer = customerService.updateCustomer(id, customerDetails);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Value("${document.storage.path}")
    String storageDir;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (customerService.existsById(id)) {

            String cdlLabe = customerService.getCustomerById(id).get().getIdentity().getCustomerDocListe()
                    .getCdlLabe();

            try {
                FileUtil.deleteContents(new File(storageDir + File.separator + cdlLabe));
                Files.deleteIfExists(Paths.get(storageDir + File.separator + cdlLabe));
            } catch (IOException e) {
                System.out.println("------------------------------" + e.getClass().toString() + ":\n" + e.getMessage());
            }

            customerService.deleteCustomer(id);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CUSTOMER> getCustomerByEmail(@PathVariable String email) {
        Optional<CUSTOMER> customer = customerService.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getEmail/{cusCode}")
    public ResponseEntity<Map<String, String>> getEmail(@PathVariable Integer cusCode) {
        return ResponseEntity.ok().body(Map.of("email", customerService.getCustomerById(cusCode).get().getCusMailAddress()));
    }
    

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CUSTOMER> getCustomerByPhone(@PathVariable String phone) {
        Optional<CUSTOMER> customer = customerService.getCustomerByPhone(phone);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{statusCode}")
    public ResponseEntity<List<CUSTOMER>> getCustomersByStatus(@PathVariable Integer statusCode) {
        List<CUSTOMER> customers = customerService.getCustomersByStatus(statusCode);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/city/{cityCode}")
    public ResponseEntity<List<CUSTOMER>> getCustomersByCity(@PathVariable Integer cityCode) {
        List<CUSTOMER> customers = customerService.getCustomersByCity(cityCode);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<List<CUSTOMER>> getCustomersByCountry(@PathVariable Integer countryCode) {
        List<CUSTOMER> customers = customerService.getCustomersByCountry(countryCode);
        return ResponseEntity.ok(customers);
    }
   
    @GetMapping("/search")
    public ResponseEntity<List<CUSTOMER>> searchCustomers(@RequestParam("word") String searchWord) {
        List<CUSTOMER> customers = customerService.searchCustomers(searchWord);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/count-by-city")
    public ResponseEntity<Map<String, Long>> getCustomerCountByCity() {
        Map<String, Long> cityCounts = customerService.getCustomerCountByCity();
        return ResponseEntity.ok(cityCounts);
    }
}