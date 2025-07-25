package com.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

public interface AuthService extends UserDetailsService {

    public boolean existsByUsername(@PathVariable String username);

    public boolean existsByEmail(@PathVariable String email);

    public boolean existsByPhone(@PathVariable String phone);

    public Optional<? extends UserDetails> findByEmail(String email);

    public boolean comapreTOTP(String email, String code);

    public void resetPassword(String email, String password);

}
