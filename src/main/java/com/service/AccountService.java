package com.service;
import com.model.ACCOUNT;
import java.util.List;
import java.util.Optional;
public interface AccountService {
	ACCOUNT createAccount(ACCOUNT account);
    ACCOUNT getAccountById(Integer id);
    List<ACCOUNT> getAllAccounts();
    ACCOUNT updateAccount(Integer id, ACCOUNT account);
    void deleteAccount(Integer id);
    List<ACCOUNT> searchAccounts(String searchWord);

}
