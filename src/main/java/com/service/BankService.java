package com.service;
import com.model.BANK;
import java.util.List;


public interface BankService {
	BANK create(BANK bank);
    BANK update(Integer id, BANK bank);
    void delete(Integer id);
    BANK getById(Integer id);
    List<BANK> getAll();
    List<BANK> searchBanks(String searchWord);

}
