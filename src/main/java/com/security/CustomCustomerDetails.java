package com.security;

import com.model.CUSTOMER;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
<<<<<<< HEAD
=======
import java.util.Collections;
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
import java.util.stream.Collectors;

public class CustomCustomerDetails implements UserDetails {
    private final CUSTOMER customer;
    public CustomCustomerDetails(CUSTOMER customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
<<<<<<< HEAD
        return customer.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
            .collect(Collectors.toList());
=======
        return Collections.singletonList(
        new SimpleGrantedAuthority("ROLE_" + customer.getRole().getName()));
        /* return customer.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList()); */
        
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    }

    public String getCusMotDePasse() {
        return customer.getCusMotDePasse();
    }

    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
<<<<<<< HEAD
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
=======
      return customer.getCusMotDePasse();
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    }
}

