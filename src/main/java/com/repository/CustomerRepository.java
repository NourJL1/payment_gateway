package com.repository;

import com.model.CITY;
import com.model.COUNTRY;
import com.model.CUSTOMER;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.model.WALLET;
import com.model.CUSTOMER_STATUS;



public interface CustomerRepository extends JpaRepository<CUSTOMER, Integer> {
        Optional<CUSTOMER> findByCusMailAddress(String email);

        Optional<CUSTOMER> findByCusPhoneNbr(String phone);

        List<CUSTOMER> findByStatus(CUSTOMER_STATUS status);

        List<CUSTOMER> findByCountry(COUNTRY country);

        List<CUSTOMER> findByCity(CITY city);

        Optional<CUSTOMER> findByUsername(String username);

        Optional<CUSTOMER> findByWallet(WALLET wallet);

        //List<CUSTOMER> findByWalletIsNotEmpty();
        //List<CUSTOMER> findByWalletsIsEmpty();

        boolean existsByCusMailAddress(String email);

        boolean existsByUsername(String username);

        boolean existsByCusPhoneNbr(String phone);

        // Recherche avancée avec Query (JPQL)
        @Query("SELECT c FROM CUSTOMER c WHERE " +
                "LOWER(c.cusFirstName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
                "LOWER(c.cusLastName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
                "LOWER(c.cusMailAddress) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
                "LOWER(c.cusPhoneNbr) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
                "LOWER(c.cusAddress) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
        List<CUSTOMER> searchCustomers(@Param("searchWord") String searchWord);
        /* @Query("SELECT c FROM CUSTOMER c WHERE " +
                        "(:name IS NULL OR c.cusFirstName LIKE %:name% OR c.cusLastName LIKE %:name%) " +
                        "AND (:email IS NULL OR c.cusMailAddress LIKE %:email%) " +
                        "AND (:phone IS NULL OR c.cusPhoneNbr LIKE %:phone%) " +
                        "AND (:cityCode IS NULL OR c.city = :cityCode) " +
                        "AND (:countryCode IS NULL OR c.country = :countryCode)")
        List<CUSTOMER> searchCustomers(@Param("name") String name,
                        @Param("email") String email,
                        @Param("phone") String phone); */

}
