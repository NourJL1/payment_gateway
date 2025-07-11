package com.servicesImp;



import java.security.InvalidKeyException;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.model.TOTP;
import com.service.TOTPService;

@Service
public class TOTPServiceImp implements TOTPService {

    TOTP totp;

    @Override
    public String generateTOTP(String email)
    {
        totp = new TOTP();
        try {
            totp.setEmail(email);
            return totp.getCode().generateOneTimePasswordString(totp.getKey(), Instant.now());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

 
    @Override
    public boolean verifyTOTP(String email, String code)
    {
        try {
            return totp.getCode().generateOneTimePasswordString(totp.getKey(), Instant.now()).equals(code) && totp.getEmail().equals(email);
        } catch (InvalidKeyException e) {
            e.getMessage();
            return false;
        }
        
    }

}
