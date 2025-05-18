package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.CUSTOMER_IDENTITY_TYPE;

public interface CustomerIdentityTypeRepository extends JpaRepository<CUSTOMER_IDENTITY_TYPE, Integer>  {

}
