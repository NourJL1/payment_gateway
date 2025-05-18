package com.controller;

import com.model.CUSTOMER;
import com.repository.CustomerRepository;
import com.security.JwtTokenUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getCusMailAddress(),
                    loginRequest.getCusPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            CUSTOMER customer = customerRepository.findByCusMailAddress(loginRequest.getCusMailAddress())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

            return ResponseEntity.ok(new LoginResponse(
                token,
                customer.getCusCode(),
                customer.getCusFirstName(),
                customer.getCusLastName(),
                customer.getCusMailAddress()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse());
        }
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;

        public String getCusMailAddress() {
            return email;
        }

        public String getCusPassword() {
            return password;
        }
    }

    @Data
    public static class LoginResponse {
        public LoginResponse(String token, Integer customerId, String firstName, String lastName, String email) {
            this.token = token;
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
        private final String token;
        private final Integer customerId;
        private final String firstName;
        private final String lastName;
        private final String email;
    }

    @Data
    public static class ErrorResponse {
        private final String message = "";
    }
} 