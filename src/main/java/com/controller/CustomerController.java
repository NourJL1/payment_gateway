package com.controller;

import com.repository.CityRepository;
import com.repository.CustomerRepository;
import com.repository.CustomerStatusRepository;
import com.service.CustomerDocListeService;
import com.service.CustomerService;
import com.service.EmailService;
import com.service.TOTPService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CUSTOMER customer) {

        // Vérifier si la ville est définie
        if (customer.getCity() == null || customer.getCity().getCtyCode() == null) {
            return ResponseEntity.badRequest().body("City information is required.");
        }

        cityRepository.findById(customer.getCity().getCtyCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "City not found"));

        if (customerRepository.existsByCusMailAddress(customer.getCusMailAddress())) {
            return ResponseEntity.badRequest().body("email already in use");
        }

        // Vérifier si le statut du client est défini
        /* if (customer.getStatus() == null || customer.getStatus().getCtsCode() == null) {
            return ResponseEntity.badRequest().body("Customer status is required.");
        } */

        // Récupérer le statut en base de données en utilisant `ctsCode`
        /* CUSTOMER_STATUS status = customerStatusRepository.findByCtsCode(customer.getStatus().getCtsCode());
        if (status == null) {
            return ResponseEntity.badRequest().body("Invalid customer status.");
        } */

        // Assigner le statut au client
        if (customer.getStatus() == null) 
            customer.setStatus(customerStatusRepository.findByCtsCode(3)); // Default status

        // Vérifier si l'identité est définie et récupérer les informations de
        // l'identité
        /* if (customer.getIdentity() != null && customer.getIdentity().getCidCode() != null) {
            Optional<CUSTOMER_IDENTITY> identityOpt = customerIdentityRepository
                    .findById(customer.getIdentity().getCidCode());
            if (identityOpt.isPresent()) {
                customer.setIdentity(identityOpt.get());
            } else {
                return ResponseEntity.badRequest().body("Customer Identity not found.");
            }
        } */

        /* if (customer.getIdentity() != null)
        {
            customerIdentityRepository.save(customer.getIdentity());
        } */

       // customer.setIdentity(customerIdentityRepository.findById(1).get());

        customerDocListeService.save(customer.getIdentity().getCustomerDocListe());

        // Réinitialiser l'ID pour éviter une mise à jour involontaire
        customer.setCusCode(null);
        customer.setWallet(new WALLET( null, null, null, null, 0f, 0f, 0f,  null, null, customer, null, null, null, null, null, null, null, null, null));


        System.out.println("------------------------------------------------"+customer.getCusMotDePasse());
        customer.setCusMotDePasse(passwordEncoder.encode(customer.getCusMotDePasse()));
        System.out.println("------------------------------------------------"+customer.getCusMotDePasse());

        // Sauvegarder le client
        CUSTOMER savedCustomer = customerRepository.save(customer);

        //savedCustomer.getWallets().forEach( wallet -> wallet.setCustomer(savedCustomer));

        //customer.getWallets().get(0).setCustomer(customer);

        System.out.println("------------------------------------------------"+savedCustomer.getCusMotDePasse());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CUSTOMER> getCustomerById(@PathVariable Integer id) {
        System.out.println("Fetching customer with ID: " + id);
        Optional<CUSTOMER> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CUSTOMER>> getAllCustomers() {
        List<CUSTOMER> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
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

    @PutMapping("resetPassword/{cusCode}")
    public ResponseEntity<Map<String, String>> resetPassword(@PathVariable Integer cusCode, @RequestBody String password) {
        CUSTOMER customer = customerService.getCustomerById(cusCode)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + cusCode));
        customer.setCusMotDePasse(passwordEncoder.encode(password));
        customerRepository.save(customer);

        System.out.println(customer.getCusMotDePasse());

        return ResponseEntity.ok().body(Map.of("message", "success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (customerService.existsById(id)) {
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

    /* @GetMapping("/with-wallets")
    public ResponseEntity<List<CUSTOMER>> getCustomersWithWallets() {
        List<CUSTOMER> customers = customerService.getCustomersWithWallets();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/without-wallets")
    public ResponseEntity<List<CUSTOMER>> getCustomersWithoutWallets() {
        List<CUSTOMER> customers = customerService.getCustomersWithoutWallets();
        return ResponseEntity.ok(customers);
    } */

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
                String roles = "ROLE_" + customer.getRole().getName(); // e.g., "ROLE_CUSTOMER"
                System.out.println("User found: cusCode=" + customer.getCusCode() + ", roles=" + roles);
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
                System.out.println("User not found: " + loginRequest.getUsername());
                return ResponseEntity.status(404)
                        .body(new ResponseDTO("User not found",null,  null, null, null, null, null, null, null));
            }
        } catch (BadCredentialsException e) {
            System.out.println("Invalid credentials for: " + loginRequest.getUsername());
            return ResponseEntity.status(401)
                    .body(new ResponseDTO("Invalid credentials", null, null, null, null, null, null, null, null));
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
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
        private String roles; // Changed from token to roles

        public ResponseDTO(String message, String cusCode, String username, String fullname, String status, Role role,
                String cusMailAddress,  String cusPhoneNbr, String roles) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    @Autowired
    EmailService emailService;
    @Autowired
    TOTPService totpService;

    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody emailDTO email) {
        String text = "";
        switch (email.getSubject()) {
            case "TOTP":
                if(customerRepository.existsByCusMailAddress(email.getCusMailAdress()))
                    return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
                text = "Your verification code is: " +
                        totpService.generateTOTP(email.getCusMailAdress()) +
                        "\n The code expires in 5 minutes.";
                break;
            case "Reset Password":
                text = "Click this link to reset your password: \n" +
                        "http://localhost:4200/reset-password\n\n" +
                        "If you didn't request this, please ignore this email.";
                break;

            default:
                break;
        }

        email.setText(text);
        String result = emailService.sendMail(
                email.getCusMailAdress(),
                email.getSubject(),
                email.getText());
        return ResponseEntity.ok().body(Map.of("message", result));
    }

    @PostMapping("/compareTOTP")
    public boolean compareTOPT(@RequestBody otpDTO otp) {
        return customerService.comapreTOTP(otp.cusMailAdress, otp.code);
    }

    public static class emailDTO {
        String cusMailAdress;
        String subject;
        String text;

        public String getCusMailAdress() {
            return cusMailAdress;
        }

        public void setCusMailAdress(String cusMailAdress) {
            this.cusMailAdress = cusMailAdress;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public static class otpDTO {
        private String cusMailAdress;
        private String code;

        public String getCusMailAdress() {
            return cusMailAdress;
        }

        public void setCusMailAdress(String cusMailAdress) {
            this.cusMailAdress = cusMailAdress;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}