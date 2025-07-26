package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    boolean existsByLogin(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
