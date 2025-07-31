package com.servicesImp;

import com.model.*;
import com.repository.*;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired 
    private AccountTypeRepository accountTypeRepository;
    
    @Autowired 
    private AccountListRepository accountListRepository;
    
    @Autowired 
    private BankRepository bankRepository;

    @Override
    public ACCOUNT createAccount(ACCOUNT account) {
        // ... (unchanged from previous version)
        if (account.getAccountType() == null || account.getAccountType().getAtyCode() == null) {
            throw new IllegalArgumentException("ACCOUNT_TYPE or its atyCode cannot be null.");
        }
        Integer atyCode = account.getAccountType().getAtyCode();
        ACCOUNT_TYPE accountType = accountTypeRepository.findById(atyCode)
                .orElseThrow(() -> new IllegalArgumentException("ACCOUNT_TYPE with atyCode " + atyCode + " not found."));
        account.setAccountType(accountType);

        if (account.getAccountList() == null || account.getAccountList().getAliCode() == null) {
            throw new IllegalArgumentException("ACCOUNT_LIST or its aliCode cannot be null.");
        }
        Integer aliCode = account.getAccountList().getAliCode();
        ACCOUNT_LIST accountList = accountListRepository.findById(aliCode)
                .orElseThrow(() -> new IllegalArgumentException("ACCOUNT_LIST with aliCode " + aliCode + " not found."));
        account.setAccountList(accountList);

        if (account.getBank() == null || account.getBank().getBanCode() == null) {
            throw new IllegalArgumentException("BANK or its banCode cannot be null.");
        }
        Integer banCode = account.getBank().getBanCode();
        BANK bank = bankRepository.findById(banCode)
                .orElseThrow(() -> new IllegalArgumentException("BANK with banCode " + banCode + " not found."));
        account.setBank(bank);

        return accountRepository.save(account);
    }

    @Override
    public ACCOUNT getAccountById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<ACCOUNT> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public ACCOUNT updateAccount(Integer id, ACCOUNT accountData) {
        // ... (unchanged from previous version)
        ACCOUNT existingAccount = accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        if (accountData.getAccountType() != null && accountData.getAccountType().getAtyCode() != null) {
            ACCOUNT_TYPE accountType = accountTypeRepository.findById(accountData.getAccountType().getAtyCode())
                .orElseThrow(() -> new RuntimeException("Account Type not found"));
            existingAccount.setAccountType(accountType);
        }
        if (accountData.getAccountList() != null && accountData.getAccountList().getAliCode() != null) {
            ACCOUNT_LIST accountList = accountListRepository.findById(accountData.getAccountList().getAliCode())
                .orElseThrow(() -> new RuntimeException("Account List not found"));
            existingAccount.setAccountList(accountList);
        }
        if (accountData.getBank() != null && accountData.getBank().getBanCode() != null) {
            BANK bank = bankRepository.findById(accountData.getBank().getBanCode())
                .orElseThrow(() -> new RuntimeException("Bank not found"));
            existingAccount.setBank(bank);
        }
        if (accountData.getAccRib() != null) {
            existingAccount.setAccRib(accountData.getAccRib());
        }
        if (accountData.getAccIden() != null) {
            existingAccount.setAccIden(accountData.getAccIden());
        }
        if (accountData.getAccKey() != null) {
            existingAccount.setAccKey(accountData.getAccKey());
        }
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        System.out.println("Deleting account with ID: " + id); // Debug log
        if (id == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account with ID " + id + " not found");
        }
        accountRepository.deleteById(id);
        System.out.println("Account deleted successfully: " + id); // Debug log
    }

    @Override
    public List<ACCOUNT> searchAccounts(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return accountRepository.findAll();
        }
        return accountRepository.searchAccounts(searchWord);
    }
}