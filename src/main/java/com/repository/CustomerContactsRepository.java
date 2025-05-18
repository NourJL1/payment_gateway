package com.repository;
import com.model.CUSTOMER_CONTACTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerContactsRepository extends JpaRepository<CUSTOMER_CONTACTS, Integer> {

}
