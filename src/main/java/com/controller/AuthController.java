package com.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.model.CUSTOMER;
import com.model.User;
import com.model.UserProfile;
import com.model.Module;
import com.model.UserProfileMenuOption;
import com.service.AuthService;
import com.service.EmailService;
import com.service.TOTPService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TOTPService totpService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
            UserDetails ud = authService.loadUserByUsername(loginRequest.getUsername());
            if (ud != null) {
                if (ud instanceof CUSTOMER) {
                    CUSTOMER customer = (CUSTOMER) ud;
                    
                    // Create WalletDTO from customer's wallet
                    WalletDTO walletDTO = null;
                    if (customer.getWallet() != null) {
                        walletDTO = new WalletDTO(
                            customer.getWallet().getWalIden(),
                            customer.getWallet().getWalLogicBalance(),
                            customer.getWallet().getWalEffBal(),
                            customer.getWallet().getWalCode()
                        );
                    }
                    
                    return ResponseEntity.ok().body(new CustomerResponseDTO(
                            customer.getCusCode().toString(),
                            customer.getUsername(),
                            customer.getFullName(),
                            customer.getStatus().getCtsLabe(),
                            customer.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()),
                            walletDTO)); // Include wallet data
                }
                // if (ud instanceof User)
                return ResponseEntity.ok().body(new UserResponseDTO(
                        ((User) ud).getCode().toString(),
                        ((User) ud).getUsername(),
                        ((User) ud).getFullName(),
                        ((User) ud).getProfile() != null ? ((User) ud).getProfile().getCode() : null,
                        ((User) ud).getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())/* ,
                        ((User) ud).getProfile().getProfileMenuOptions() */));
            } else
                return ResponseEntity.status(404).body("User not found");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
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

    
    public class CustomerResponseDTO {
        private String cusCode;
        private String username;
        private String fullname;
        private String status;
        private Collection<String> authorities;
        private WalletDTO wallet; // Add this field

        // Constructor with wallet
        public CustomerResponseDTO(String cusCode, String username, String fullname, String status,
                Collection<String> authorities, WalletDTO wallet) {
            this.cusCode = cusCode;
            this.username = username;
            this.fullname = fullname;
            this.status = status;
            this.authorities = authorities;
            this.wallet = wallet;
        }

        // Getters and setters
        public WalletDTO getWallet() {
            return wallet;
        }

        public void setWallet(WalletDTO wallet) {
            this.wallet = wallet;
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
		public Collection<String> getAuthorities() {
			return authorities;
		}
		public void setAuthorities(Collection<String> authorities) {
			this.authorities = authorities;
		}
		public CustomerResponseDTO(String cusCode, String username, String fullname, String status,
				Collection<String> authorities) {
			super();
			this.cusCode = cusCode;
			this.username = username;
			this.fullname = fullname;
			this.status = status;
			this.authorities = authorities;
		}
		
		
        
    }
 // Add this WalletDTO class
    public static class WalletDTO {
        private String walIden;
        private Float walLogicBalance;
        private Float walEffBal;
        private Integer walCode; // Add this field

        public WalletDTO(String walIden, Float walLogicBalance, Float walEffBal, Integer walCode) {
            this.walIden = walIden;
            this.walLogicBalance = walLogicBalance;
            this.walEffBal = walEffBal;
            this.walCode = walCode;
        }

        // Getters and setters
        public Integer getWalCode() {
            return walCode;
        }
        public void setWalCode(Integer walCode) {
            this.walCode = walCode;
        }
        public String getWalIden() {
            return walIden;
        }

        public void setWalIden(String walIden) {
            this.walIden = walIden;
        }

        public Float getWalLogicBalance() {
            return walLogicBalance;
        }

        public void setWalLogicBalance(Float walLogicBalance) {
            this.walLogicBalance = walLogicBalance;
        }

        public Float getWalEffBal() {
            return walEffBal;
        }

        public void setWalEffBal(Float walEffBal) {
            this.walEffBal = walEffBal;
        }
    }

   
    public class UserResponseDTO {
        private String useCode;
        private String username;
        private String fullname;
        private Integer profileCode;
        private Collection<String> authorities;

        public UserResponseDTO(String useCode, String username, String fullname, Integer profileCode, Collection<String> authorities) {
            this.useCode = useCode;
            this.username = username;
            this.fullname = fullname;
            this.profileCode = profileCode;
            this.authorities = authorities;
        }
        public UserResponseDTO(String useCode, String username, String fullname, Collection<String> authorities) {
            this.useCode = useCode;
            this.username = username;
            this.fullname = fullname;
            this.authorities = authorities;
        }
        public Integer getProfileCode() {
            return profileCode;
        }
        public void setProfileCode(Integer profileCode) {
            this.profileCode = profileCode;
        }
        public String getUseCode() {
			return useCode;
		}
		public void setUseCode(String useCode) {
			this.useCode = useCode;
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
		public Collection<String> getAuthorities() {
			return authorities;
		}
		public void setAuthorities(Collection<String> authorities) {
			this.authorities = authorities;
		}
    }

    
    @PutMapping("resetPassword/{email}")
    public ResponseEntity<Map<String, String>> resetPassword(@PathVariable String email,
            @RequestBody String password) {
        authService.resetPassword(email, passwordEncoder.encode(password));
        return ResponseEntity.ok().body(Map.of("message", "success"));
    }

    @GetMapping("existsByUsername/{username}")
    public boolean existsByUsername(@PathVariable String username) {
        return authService.existsByUsername(username);
    }

    @GetMapping("existsByEmail/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return authService.existsByEmail(email);
    }

    @GetMapping("existsByPhone/{phone}")
    public boolean existsByPhone(@PathVariable String phone) {
        return authService.existsByPhone(phone);
    }

    @GetMapping("getByEmail/{email}")
    public ResponseEntity<? extends UserDetails> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(authService.findByEmail(email).get());
    }
    

    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody emailDTO email) {
        String text = "";
        switch (email.getSubject()) {
            case "register":
                // if (customerRepository.existsByCusMailAddress(email.getCusMailAdress()))
                if (authService.existsByEmail(email.cusMailAdress))
                    return ResponseEntity.ok().body(Map.of("message", "Email already exists"));
                text = "Your verification code is: " +
                        totpService.generateTOTP(email.getCusMailAdress()) +
                        "\n The code expires in 5 minutes.";
                break;
            case "confirm":
                // if (!customerRepository.existsByCusMailAddress(email.getCusMailAdress()))
                if (!authService.existsByEmail(email.cusMailAdress))
                    return ResponseEntity.ok().body(Map.of("message", "Email doesn't exist in our system"));
                text = "Your verification code is: " +
                        totpService.generateTOTP(email.getCusMailAdress()) +
                        "\n The code expires in 5 minutes.";
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
        return authService.comapreTOTP(otp.cusMailAdress, otp.code);
    }

    private static class emailDTO {
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

    private static class otpDTO {
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
