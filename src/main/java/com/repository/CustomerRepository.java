package com.repository;
import com.model.CUSTOMER;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<CUSTOMER, Integer>{
	Optional<CUSTOMER> findByCusMailAddress(String email);

    Optional<CUSTOMER> findByCusPhoneNbr(String phone);

    List<CUSTOMER> findByCity_CtyCode(Integer cityCode);

    List<CUSTOMER> findByCity_Country_CtrCode(Integer countryCode);

    List<CUSTOMER> findByWalletsIsNotEmpty();

    List<CUSTOMER> findByWalletsIsEmpty();

    // Recherche avancée avec Query (JPQL)
    @Query("SELECT c FROM CUSTOMER c WHERE " +
            "(:name IS NULL OR c.cusFirstName LIKE %:name% OR c.cusLastName LIKE %:name%) " +
            "AND (:email IS NULL OR c.cusMailAddress LIKE %:email%) " +
            "AND (:phone IS NULL OR c.cusPhoneNbr LIKE %:phone%) " +
            "AND (:cityCode IS NULL OR c.city.ctyCode = :cityCode) " +
            "AND (:countryCode IS NULL OR c.city.country.ctrCode = :countryCode)")
    List<CUSTOMER> searchCustomers(@Param("name") String name,
                                   @Param("email") String email,
                                   @Param("phone") String phone,
                                   @Param("cityCode") Integer cityCode,
                                   @Param("countryCode") Integer countryCode);
}

