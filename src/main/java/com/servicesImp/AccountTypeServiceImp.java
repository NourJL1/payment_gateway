package com.servicesImp;
import com.model.ACCOUNT_TYPE;
import com.repository.AccountTypeRepository;
import com.service.AccountTypeService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService {

	@Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public ACCOUNT_TYPE create(ACCOUNT_TYPE accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public ACCOUNT_TYPE update(Integer id, ACCOUNT_TYPE accountType) {
        Optional<ACCOUNT_TYPE> existingAccountType = accountTypeRepository.findById(id);
        if (existingAccountType.isPresent()) {
            ACCOUNT_TYPE updatedAccountType = existingAccountType.get();
            updatedAccountType.setAtyIden(accountType.getAtyIden());
            updatedAccountType.setAtyLabe(accountType.getAtyLabe());
            updatedAccountType.setAtyFinId(accountType.getAtyFinId());
            return accountTypeRepository.save(updatedAccountType);
        } else {
            throw new RuntimeException("ACCOUNT_TYPE not found with id: " + id);
        }
    }

    @Override
    public void delete(Integer id) {
        accountTypeRepository.deleteById(id);
    }

    @Override
    public ACCOUNT_TYPE getById(Integer id) {
        return accountTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ACCOUNT_TYPE not found with id: " + id));
    }

    @Override
    public List<ACCOUNT_TYPE> getAll() {
        return accountTypeRepository.findAll();
    }
}
