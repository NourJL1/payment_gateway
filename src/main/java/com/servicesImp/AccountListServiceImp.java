package com.servicesImp;

import com.model.ACCOUNT_LIST;
import com.repository.AccountListRepository;
import com.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListServiceImp implements AccountListService {

    @Autowired
    private AccountListRepository accountListRepository;

    @Override
    public ACCOUNT_LIST create(ACCOUNT_LIST accountList) {
        return accountListRepository.save(accountList);
    }

    @Override
    public ACCOUNT_LIST update(Integer id, ACCOUNT_LIST accountList) {
        return accountListRepository.findById(id).map(existingAccountList -> {
            existingAccountList.setAliIden(accountList.getAliIden());
            existingAccountList.setAliLabe(accountList.getAliLabe());
            existingAccountList.setWallet(accountList.getWallet());
            existingAccountList.setAccounts(accountList.getAccounts());
            return accountListRepository.save(existingAccountList);
        }).orElseThrow(() -> new RuntimeException("AccountList not found"));
    }

    @Override
    public void delete(Integer id) {
        if (accountListRepository.existsById(id)) {
            accountListRepository.deleteById(id);
        } else {
            throw new RuntimeException("AccountList not found");
        }
    }

    @Override
    public ACCOUNT_LIST getById(Integer id) {
        return accountListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountList not found"));
    }

    @Override
    public List<ACCOUNT_LIST> getAll() {
        return accountListRepository.findAll();
    }
    @Override
    public List<ACCOUNT_LIST> searchAccountLists(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return accountListRepository.findAll();
        }
        return accountListRepository.searchAccountLists(searchWord);
    }
}
