package com.repository;

import com.model.CITY;
import com.model.COUNTRY;
import com.model.CUSTOMER;
import com.model.CUSTOMER_STATUS;
import com.model.WALLET;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CUSTOMER, Integer> {
    Optional<CUSTOMER> findByCusMailAddress(String email);
    Optional<CUSTOMER> findByCusPhoneNbr(String phone);
    List<CUSTOMER> findByStatus(CUSTOMER_STATUS status);
    List<CUSTOMER> findByCountry(COUNTRY country);
    List<CUSTOMER> findByCity(CITY city);
    Optional<CUSTOMER> findByUsername(String username);
    Optional<CUSTOMER> findByWallet(WALLET wallet);
    boolean existsByCusMailAddress(String email);
    long count();
    long countByStatus(CUSTOMER_STATUS status);

    // Use native query to handle date truncation for counting customers created today
    @Query(value = "SELECT COUNT(*) FROM customers c WHERE DATE(c.created_at) = CURRENT_DATE", nativeQuery = true)
    long countCustomersCreatedToday();

    // Count customers created within a date range (JPQL, works with LocalDateTime)
    @Query("SELECT COUNT(c) FROM CUSTOMER c WHERE c.createdAt >= :startDate AND c.createdAt < :endDate")
    long countCustomersByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT c FROM CUSTOMER c WHERE " +
            "LOWER(c.cusFirstName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(c.cusLastName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(c.cusMailAddress) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(c.cusPhoneNbr) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(c.cusAddress) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<CUSTOMER> searchCustomers(@Param("searchWord") String searchWord);
}