package com.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.model.CUSTOMER;
import com.model.User;
import com.repository.CustomerRepository;
import com.repository.UserRepository;
import com.service.AuthService;
import com.service.TOTPService;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private TOTPService totpService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<CUSTOMER> customer = customerRepo.findByUsername(username);
        if (customer.isPresent())
            return customer.get();

        Optional<User> user = userRepo.findByLogin(username);
        if (user.isPresent())
            return user.get();

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public boolean existsByUsername(@PathVariable String username) {
        return customerRepo.existsByUsername(username) || userRepo.existsByLogin(username);
    }

    @Override
    public boolean existsByEmail(@PathVariable String email) {
        return customerRepo.existsByCusMailAddress(email) || userRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(@PathVariable String phone) {
        return customerRepo.existsByCusPhoneNbr(phone) || userRepo.existsByPhone(phone);
    }

    @Override
    public Optional<? extends UserDetails> findByEmail(String email) {
        Optional<CUSTOMER> customer = customerRepo.findByCusMailAddress(email);
        if (customer.isPresent())
            return customer;

        return userRepo.findByEmail(email);
    }

    @Override
    public boolean comapreTOTP(String email, String code) {
        return totpService.verifyTOTP(email, code);
    }

    @Override
    public void resetPassword(String email, String password) {
        Optional<CUSTOMER> customer = customerRepo.findByCusMailAddress(email);
        if (customer.isPresent()) {
            customer.get().setCusMotDePasse(password);
            customerRepo.save(customer.get());
            return;
        }
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            user.get().setPassword(password);
            userRepo.save(user.get());
            return;
        }
        throw new RuntimeException("not found with email: " + email);
    }
}
