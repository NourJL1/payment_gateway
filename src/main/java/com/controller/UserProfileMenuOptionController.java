package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.model.UserProfile;
import com.model.UserProfileMenuOption;
import com.service.UserProfileMenuOptionService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/user-profile-menu-options")
public class UserProfileMenuOptionController {

    @Autowired
    UserProfileMenuOptionService userProfileMenuOptionService;

    // Create
    @PostMapping
    public ResponseEntity<UserProfileMenuOption> createProfileMenuOption(
            @RequestBody UserProfileMenuOption profileMenuOption) {
        UserProfileMenuOption created = userProfileMenuOptionService.createProfileMenuOption(profileMenuOption);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileMenuOption> getProfileMenuOptionById(@PathVariable Integer id) {
        return userProfileMenuOptionService.getProfileMenuOptionById(id)
                .map(option -> new ResponseEntity<>(option, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserProfileMenuOption>> getAllProfileMenuOptions() {
        List<UserProfileMenuOption> options = userProfileMenuOptionService.getAllProfileMenuOptions();
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileMenuOption> updateProfileMenuOption(
            @PathVariable Integer id,
            @RequestBody UserProfileMenuOption profileMenuOption) {
        try {
            UserProfileMenuOption updated = userProfileMenuOptionService.updateProfileMenuOption(id, profileMenuOption);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Set or Update Profile-MenuOption relationship
    @PutMapping("/set")
    public ResponseEntity<UserProfileMenuOption> setProfileMenuOption(
            @RequestBody UserProfileMenuOption profileMenuOption) {
        UserProfileMenuOption result = userProfileMenuOptionService.updateProfileMenuOption(profileMenuOption);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileMenuOption(@PathVariable Integer id) {
        userProfileMenuOptionService.deleteProfileMenuOption(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByProfile/{profileCode}")
    public ResponseEntity<List<UserProfileMenuOption>> getByProfile(@PathVariable Integer profileCode) {
        return ResponseEntity.ok().body(userProfileMenuOptionService.getByProfile(profileCode));
    }

    @GetMapping("profile/{profileCode}/module/{moduleCode}")
    public ResponseEntity<List<UserProfileMenuOption>> getByProfileAndModule(@PathVariable Integer profileCode, @PathVariable Integer moduleCode) {
        return ResponseEntity.ok().body(userProfileMenuOptionService.getByProfileAndModule(profileCode, moduleCode));
    }
    
    

}
