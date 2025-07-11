package com.repository;
import com.model.CUSTOMER_IDENTITY;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerIdentityRepository extends JpaRepository<CUSTOMER_IDENTITY, Integer> {

}
