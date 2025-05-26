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
    
<<<<<<< HEAD
    static Optional<CUSTOMER> findByUsername(String username) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

=======
    Optional<CUSTOMER> findByUsername(String username);
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d



    List<CUSTOMER> findByWalletsIsNotEmpty();

    List<CUSTOMER> findByWalletsIsEmpty();

    // Recherche avancée avec Query (JPQL)
    @Query("SELECT c FROM CUSTOMER c WHERE " +
            "(:name IS NULL OR c.cusFirstName LIKE %:name% OR c.cusLastName LIKE %:name%) " +
            "AND (:email IS NULL OR c.cusMailAddress LIKE %:email%) " +
<<<<<<< HEAD
            "AND (:phone IS NULL OR c.cusPhoneNbr LIKE %:phone%) " )
=======
            "AND (:phone IS NULL OR c.cusPhoneNbr LIKE %:phone%) " +
            "AND (:cityCode IS NULL OR c.city = :cityCode) " +
            "AND (:countryCode IS NULL OR c.country = :countryCode)")
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    List<CUSTOMER> searchCustomers(@Param("name") String name,
                                   @Param("email") String email,
                                   @Param("phone") String phone);

   
}

