package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByIdentifier(String identifier);

    //findByUser

    @Query("SELECT up FROM UserProfile up WHERE " +
	           "LOWER(up.identifier) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(up.label) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<UserProfile> search(String searchWord);
}
