package com.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.ACCOUNT_TYPE;

public interface AccountTypeRepository extends JpaRepository<ACCOUNT_TYPE, Integer> {


}
