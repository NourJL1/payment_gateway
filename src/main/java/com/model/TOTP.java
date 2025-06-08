package com.model;


import java.time.Duration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;




public class TOTP {

    private SecretKey key;
    private TimeBasedOneTimePasswordGenerator code;
    private String email;

    public TOTP()
    {
        this.code = new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(150));
        try {
            this.key = KeyGenerator.getInstance(code.getAlgorithm()).generateKey();
        } catch (Exception e) {
           this.key = null;
        }
    }

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

	public TimeBasedOneTimePasswordGenerator getCode() {
		return code;
	}

	public void setCode(TimeBasedOneTimePasswordGenerator code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
