package com.service;
import com.model.BANK;
import java.util.List;
import java.util.Optional;


public interface BankService {
	BANK create(BANK bank);
    BANK update(Integer id, BANK bank);
    void delete(Integer id);
    BANK getById(Integer id);
    List<BANK> getAll();

}
