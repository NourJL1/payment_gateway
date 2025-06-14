package com.servicesImp;

import com.model.BANK;
import com.repository.BankRepository;
import com.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImp implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public BANK create(BANK bank) {
        return bankRepository.save(bank);
    }

    @Override
    public BANK update(Integer id, BANK bank) {
        BANK existingBank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BANK not found with id: " + id));

        existingBank.setBanIden(bank.getBanIden());
        existingBank.setBanCorpName(bank.getBanCorpName());
        existingBank.setBanInit(bank.getBanInit());
        existingBank.setBanFinId(bank.getBanFinId());

        return bankRepository.save(existingBank);
    }

    @Override
    public void delete(Integer id) {
        if (!bankRepository.existsById(id)) {
            throw new RuntimeException("BANK not found with id: " + id);
        }
        bankRepository.deleteById(id);
    }

    @Override
    public BANK getById(Integer id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BANK not found with id: " + id));
    }

    @Override
    public List<BANK> getAll() {
        return bankRepository.findAll();
    }
    @Override
    public List<BANK> searchBanks(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return bankRepository.findAll();
        }
        return bankRepository.searchBanks(searchWord);
    }
}
