package com.security;

import com.model.CUSTOMER;
import com.model.Role;
import com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CUSTOMER customer = customerRepository.findByCusMailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        List<SimpleGrantedAuthority> authorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCusFirstName()))
                .collect(Collectors.toList());

        return new User(
            customer.getCusMailAddress(),
            customer.getCusPassword(),
            authorities
        );
    }
} 