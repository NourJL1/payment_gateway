package com.service;

public interface TOTPService {

	public String generateTOTP(String email);
    public boolean verifyTOTP(String email, String code);

}
