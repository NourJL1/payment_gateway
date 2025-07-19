package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
