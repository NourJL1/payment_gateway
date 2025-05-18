package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.BANK;

public interface BankRepository extends JpaRepository<BANK, Integer> {

}
