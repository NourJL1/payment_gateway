package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    boolean existsByLogin(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.identifier) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.login) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.middleName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(CONCAT(u.firstName, ' ', COALESCE(u.middleName, ''), u.lastName)) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.function) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.statusCode) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.phone) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(u.fax) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<User> search(String searchWord);

}
