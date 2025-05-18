package com.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.ACCOUNT;
import com.model.*;
public interface AccountRepository extends JpaRepository<ACCOUNT, Integer> {
	
//	public Optional<ACCOUNT_TYPE> findById(Integer atyFinId);

	
	

}
