package com.controller;
import com.repository.CityRepository;
import com.repository.CustomerIdentityRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerService;
import com.service.TOTPService;
import com.servicesImp.CustomerServiceImp;
import com.servicesImp.TOTPServiceImp;
import com.model.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	

	    @Autowired
	    private CustomerService customerService;
	    
	    @Autowired
	    private TOTPServiceImp totpServiceImp;
	    
	    @Autowired 
	    private CustomerRepository customerRepository;
	    
	    @Autowired 
	    private CityRepository cityRepository;
	  
	    
	    @Autowired 
	    private CustomerStatusRepository customerStatusRepository;
	    
	    @Autowired
	    private CustomerIdentityRepository customerIdentityRepository;
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @PostMapping ("/register")
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

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			customer.setCusMotDePasse(encoder.encode(customer.getCusMotDePasse()));

	        // Sauvegarder le client
	        CUSTOMER savedCustomer = customerRepository.save(customer);

	        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	    }
	    
	    @PostMapping("/TOTP")
	    public boolean emailTOTP(@RequestBody otpDTO otp) {
	        return customerService.comapreTOTP(otp.cusMailAdress, otp.totp);
	    }
	    public static class otpDTO{
	    	private String cusMailAdress;
	    	private String totp;
	    	
			public String getCusMailAdress() {
				return cusMailAdress;
			}
			public void setCusMailAdress(String cusMailAdress) {
				this.cusMailAdress = cusMailAdress;
			}
			public String getTotp() {
				return totp;
			}
			public void setTotp(String totp) {
				this.totp = totp;
			}
	    	
	    	
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
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
			//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			//boolean isMatch = encoder.matches(loginRequest.getCusMotDePasse(), "$2a$10$N9qo8uLOickgx2ZMRZoMy.Mrq7J4WY0lKj7fBvE3nQJ5XU1qjY1zK");
			//System.out.println("--------------------------------------------"+isMatch);
	        try {

				//

	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            loginRequest.getUsername(),
	                            loginRequest.getPassword()
	                    )
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);

	            Optional<CUSTOMER> userOpt = customerRepository.findByUsername(loginRequest.getUsername());

	            if (userOpt.isPresent()) {
	                CUSTOMER customer = userOpt.get();
	                return ResponseEntity.ok(new ResponseDTO(
	                        "Login successful",
	                        customer.getCusCode().toString(),
	                        customer.getUsername(),
	                        customer.getFullName(),
	                        customer.getRole(),
	                        customer.getCusMailAddress(),
	                        customer.getIdentificationType(),
	                        customer.getCusPhoneNbr()
	                ));
	            } else {
	                return ResponseEntity.status(404).body(new ResponseDTO("User not found", null, null, null, null, null, null, null));
	            }

	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(401).body(new ResponseDTO("Invalid credentials", null, null, null, null, null, null, null));
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage(), null, null, null, null, null, null,null));
	        }
	    }
	    
	 // DTO class for login
	    public static class LoginRequest {
	        private String username;
	        private String password;

	        // Getters and setters
	        public String getUsername() {
	            return username;
	        }

	        public void setUsername(String username) {
	            this.username = username;
	        }

	        public String getPassword() {
	            return password;
	        }

	        public void setPassword(String cusMotDePasse) {
	            this.password = cusMotDePasse;
	        }
	    }
	    
	    
	    // DTO de réponse
	    public static class ResponseDTO {
	        private String message;
	        private String cusCode;
	        private String username;
	        private String fullname;
	        private String cusPhoneNbr;
	        private String cusMailAddress;
	        private IdentificationType identificationType;
	        private Role role;

	        public ResponseDTO(String message, String cusCode, String username, String fullname, Role role, String cusMailAddress, IdentificationType identificationType, String cusPhoneNbr) {
	            this.message = message;
	            this.cusCode = cusCode;
	            this.username = username;
	            this.fullname = fullname;
	            this.role = role;
	            this.cusMailAddress = cusMailAddress;
	            this.cusPhoneNbr = cusPhoneNbr;
	            this.setIdentificationType(identificationType);
	        }

	       

	        public String getMessage() {
	        	return message;
	        }
	        public void setMessage(String message) {
	        	this.message = message;
	        }


			public String getCusCode() {
	            return cusCode;
	        }

	        public void setCusCode(String cusCode) {
	            this.cusCode = cusCode;
	        }

	        public String getUsername() {
	            return username;
	        }

	        public void setUsername(String username) {
	            this.username = username;
	        }

	        public String getFullname() {
	            return fullname;
	        }

	        public void setFullname(String fullname) {
	            this.fullname = fullname;
	        }

	        public String getCusPhoneNbr() {
	            return cusPhoneNbr;
	        }

	        public void setCusPhoneNbr(String cusPhoneNbr) {
	            this.cusPhoneNbr = cusPhoneNbr;
	        }

	        public String getCusMailAddress() {
	            return cusMailAddress;
	        }

	        public void setCusMailAddress(String cusMailAddress) {
	            this.cusMailAddress = cusMailAddress;
	        }

	        public Role getRole() {
	            return role;
	        }

	        public void setRole(Role role) {
	            this.role = role;
	        }

			public IdentificationType getIdentificationType() {
				return identificationType;
			}

			public void setIdentificationType(IdentificationType identificationType) {
				this.identificationType = identificationType;
			}
	    }

}
