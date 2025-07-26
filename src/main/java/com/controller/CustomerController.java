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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                            
        customerDocListeService.save(customer.getIdentity().getCustomerDocListe());
        customer.setCusCode(null);
        customer.setCusMotDePasse(passwordEncoder.encode(customer.getCusMotDePasse()));
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
    public ResponseEntity<Long> getNewCustomersToday() {
        long count = customerService.getNewCustomersToday();
        return ResponseEntity.ok(count);
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

    /*
     * @GetMapping("/with-wallets")
     * public ResponseEntity<List<CUSTOMER>> getCustomersWithWallets() {
     * List<CUSTOMER> customers = customerService.getCustomersWithWallets();
     * return ResponseEntity.ok(customers);
     * }
     * 
     * @GetMapping("/without-wallets")
     * public ResponseEntity<List<CUSTOMER>> getCustomersWithoutWallets() {
     * List<CUSTOMER> customers = customerService.getCustomersWithoutWallets();
     * return ResponseEntity.ok(customers);
     * }
     */

    /*
     * @GetMapping("/search")
     * public ResponseEntity<List<CUSTOMER>> searchCustomers(
     * 
     * @RequestParam(required = false) String name,
     * 
     * @RequestParam(required = false) String email,
     * 
     * @RequestParam(required = false) String phone,
     * 
     * @RequestParam(required = false) Integer cityCode,
     * 
     * @RequestParam(required = false) Integer countryCode) {
     * List<CUSTOMER> customers = customerService.searchCustomers(name, email,
     * phone, cityCode, countryCode);
     * return ResponseEntity.ok(customers);
     * }
     */

    @GetMapping("/search")
    public ResponseEntity<List<CUSTOMER>> searchCustomers(@RequestParam("word") String searchWord) {
        List<CUSTOMER> customers = customerService.searchCustomers(searchWord);
        return ResponseEntity.ok(customers);
    }

/*     @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt: username=" + loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            System.out.println("Authentication successful: " + loginRequest.getUsername());
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Optional<CUSTOMER> userOpt = customerRepository.findByUsername(loginRequest.getUsername());
            if (userOpt.isPresent()) {
                CUSTOMER customer = userOpt.get();
                String roles = "ROLE_" + customer.getRole().getName();
                return ResponseEntity.ok(new ResponseDTO(
                        "Login successful",
                        customer.getCusCode().toString(),
                        customer.getUsername(),
                        customer.getFullName(),
                        customer.getStatus().getCtsLabe(),
                        customer.getRole(),
                        customer.getCusMailAddress(),
                        customer.getCusPhoneNbr(),
                        roles));
            } else {
                return ResponseEntity.status(404)
                        .body(new ResponseDTO("User not found", null, null, null, null, null, null, null, null));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(new ResponseDTO("Invalid credentials", null, null, null, null, null, null, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseDTO(e.getMessage(), null, null, null, null, null, null, null, null));
        }
    }


    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ResponseDTO {
        private String message;
        private String cusCode;
        private String username;
        private String fullname;
        private String status;
        private Role role;
        private String cusMailAddress;
        private String cusPhoneNbr;
        private String roles;

        public ResponseDTO(String message, String cusCode, String username, String fullname, String status, Role role,
                String cusMailAddress, String cusPhoneNbr, String roles) {
            this.message = message;
            this.cusCode = cusCode;
            this.username = username;
            this.fullname = fullname;
            this.status = status;
            this.role = role;
            this.cusMailAddress = cusMailAddress;
            this.cusPhoneNbr = cusPhoneNbr;
            this.roles = roles;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public String getCusMailAddress() {
            return cusMailAddress;
        }

        public void setCusMailAddress(String cusMailAddress) {
            this.cusMailAddress = cusMailAddress;
        }

        public String getCusPhoneNbr() {
            return cusPhoneNbr;
        }

        public void setCusPhoneNbr(String cusPhoneNbr) {
            this.cusPhoneNbr = cusPhoneNbr;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }
    }
 */
}