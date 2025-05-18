package com.controller;
import com.model.CUSTOMER;
import com.repository.CityRepository;
import com.repository.CustomerIdentityRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerService;
import com.model.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
	    private CustomerIdentityRepository customerIdentityRepository;
	    
	    

	    @PostMapping
	    public ResponseEntity<?> createCustomer(@Valid @RequestBody CUSTOMER customer) {
	        // Vérifier si la ville est définie
	        if (customer.getCity() == null || customer.getCity().getCtyCode() == null) {
	            return ResponseEntity.badRequest().body("City information is required.");
	        }

	        // Vérifier si la ville existe en base de données
	        CITY city = cityRepository.findById(customer.getCity().getCtyCode())
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "City not found"));

	        // Vérifier si le statut du client est défini
	        if (customer.getStatus() == null || customer.getStatus().getCtsCode() == null) {
	            return ResponseEntity.badRequest().body("Customer status is required.");
	        }

	        // Récupérer le statut en base de données en utilisant `ctsCode`
	        CUSTOMER_STATUS status = customerStatusRepository.findByCtsCode(customer.getStatus().getCtsCode());
	        if (status == null) {
	            return ResponseEntity.badRequest().body("Invalid customer status.");
	        }

	        // Assigner le statut au client
	        customer.setStatus(status);

	        // Vérifier si l'identité est définie et récupérer les informations de l'identité
	        if (customer.getIdentity() != null && customer.getIdentity().getCidCode() != null) {
	            Optional<CUSTOMER_IDENTITY> identityOpt = customerIdentityRepository.findById(customer.getIdentity().getCidCode());
	            if (identityOpt.isPresent()) {
	                customer.setIdentity(identityOpt.get());  // Assigner l'identité au client
	            } else {
	                return ResponseEntity.badRequest().body("Customer Identity not found.");
	            }
	        }

	        // Réinitialiser l'ID pour éviter une mise à jour involontaire
	        customer.setCusCode(null);

	        // Sauvegarder le client
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

	    // ✅ Récupérer tous les clients
	    @GetMapping
	    public ResponseEntity<List<CUSTOMER>> getAllCustomers() {
	        List<CUSTOMER> customers = customerService.getAllCustomers();
	        return ResponseEntity.ok(customers);
	    }

	    // ✅ Mettre à jour un client
	    @PutMapping("/{id}")
	    public ResponseEntity<CUSTOMER> updateCustomer(@PathVariable Integer id, @RequestBody CUSTOMER customerDetails) {
	        try {
	            CUSTOMER updatedCustomer = customerService.updateCustomer(id, customerDetails);
	            return ResponseEntity.ok(updatedCustomer);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // ✅ Supprimer un client
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
	        if (customerService.existsById(id)) {
	            customerService.deleteCustomer(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // ✅ Récupérer un client par email
	    @GetMapping("/email/{email}")
	    public ResponseEntity<CUSTOMER> getCustomerByEmail(@PathVariable String email) {
	        Optional<CUSTOMER> customer = customerService.getCustomerByEmail(email);
	        return customer.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // ✅ Récupérer un client par téléphone
	    @GetMapping("/phone/{phone}")
	    public ResponseEntity<CUSTOMER> getCustomerByPhone(@PathVariable String phone) {
	        Optional<CUSTOMER> customer = customerService.getCustomerByPhone(phone);
	        return customer.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // ✅ Récupérer les clients d'une ville
	    @GetMapping("/city/{cityCode}")
	    public ResponseEntity<List<CUSTOMER>> getCustomersByCity(@PathVariable Integer cityCode) {
	        List<CUSTOMER> customers = customerService.getCustomersByCity(cityCode);
	        return ResponseEntity.ok(customers);
	    }

	    // ✅ Récupérer les clients d'un pays
	    @GetMapping("/country/{countryCode}")
	    public ResponseEntity<List<CUSTOMER>> getCustomersByCountry(@PathVariable Integer countryCode) {
	        List<CUSTOMER> customers = customerService.getCustomersByCountry(countryCode);
	        return ResponseEntity.ok(customers);
	    }

	    // ✅ Récupérer les clients ayant au moins un portefeuille
	    @GetMapping("/with-wallets")
	    public ResponseEntity<List<CUSTOMER>> getCustomersWithWallets() {
	        List<CUSTOMER> customers = customerService.getCustomersWithWallets();
	        return ResponseEntity.ok(customers);
	    }

	    // ✅ Récupérer les clients sans portefeuille
	    @GetMapping("/without-wallets")
	    public ResponseEntity<List<CUSTOMER>> getCustomersWithoutWallets() {
	        List<CUSTOMER> customers = customerService.getCustomersWithoutWallets();
	        return ResponseEntity.ok(customers);
	    }

	    // ✅ Recherche flexible avec plusieurs critères
	    @GetMapping("/search")
	    public ResponseEntity<List<CUSTOMER>> searchCustomers(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) String email,
	            @RequestParam(required = false) String phone,
	            @RequestParam(required = false) Integer cityCode,
	            @RequestParam(required = false) Integer countryCode) {

	        List<CUSTOMER> customers = customerService.searchCustomers(name, email, phone, cityCode, countryCode);
	        return ResponseEntity.ok(customers);
	    }

}
