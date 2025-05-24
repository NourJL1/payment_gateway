package com.security;

import com.model.CUSTOMER;
import com.model.Role;
import com.repository.CustomerRepository;
import com.repository.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomCustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customeruserRepository;

    public CustomCustomerDetailsService(CustomerRepository customeruserRepository) {
        this.customeruserRepository = customeruserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CUSTOMER customer = CustomerRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
            .withUsername(customer.getUsername())
            .password(customer.getCusMotDePasse())
            .authorities(customer.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName().name())) // Convert RoleName enum to String for SimpleGrantedAuthority
                            .collect(Collectors.toList()))
            .build();
    }
  }
