package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.MenuOption;
import com.model.Module;
import com.model.UserProfile;
import com.service.UserProfileService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    // Create
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile createdProfile = userProfileService.createUserProfile(userProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Integer id) {
        return userProfileService.getUserProfileById(id)
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getByIdentifier/{identifier}")
    public ResponseEntity<UserProfile>  getByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.ok().body(userProfileService.getByIdentifier(identifier)) ;
    }
    

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> profiles = userProfileService.getAllUserProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/getByUserCode/{code}")
    public ResponseEntity<UserProfile> getByUserCode(@PathVariable Integer code) {
        UserProfile profile = userProfileService.getByUserCode(code);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable Integer id, 
            @RequestBody UserProfile userProfile) {
        try {
            UserProfile updatedProfile = userProfileService.updateUserProfile(id, userProfile);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Integer id) {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Permission checks
    @GetMapping("/{id}/can-view-merchant")
    public ResponseEntity<Boolean> canViewCustomerMerchant(@PathVariable Integer id) {
        boolean canView = userProfileService.hasPermissionToViewCustomerMerchant(id);
        return new ResponseEntity<>(canView, HttpStatus.OK);
    }

    @GetMapping("/{id}/can-decrypt-pan")
    public ResponseEntity<Boolean> canDecryptPan(@PathVariable Integer id) {
        boolean canDecrypt = userProfileService.canUserProfileDecryptPan(id);
        return new ResponseEntity<>(canDecrypt, HttpStatus.OK);
    }

    // Module management
    @PostMapping("/{profileId}/Module/{moduleId}")
    public ResponseEntity<UserProfile> addModuleToProfile(
            @PathVariable Integer profileId,
            @PathVariable Integer moduleId) {
        try {
            UserProfile profile = userProfileService.addModuleToProfile(profileId, moduleId);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{profileId}/Module/{moduleId}")
    public ResponseEntity<UserProfile> removeModuleFromProfile(
            @PathVariable Integer profileId,
            @PathVariable Integer moduleId) {
        try {
            UserProfile profile = userProfileService.removeModuleFromProfile(profileId, moduleId);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Additional endpoints
    @GetMapping("/with-module/{moduleCode}")
    public ResponseEntity<List<UserProfile>> getProfilesWithModuleAccess(
            @PathVariable Integer moduleCode) {
        List<UserProfile> profiles = userProfileService.getUserProfilesWithAccessToModule(moduleCode);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }/* 

    @GetMapping("/get-Module/{code}")
    public ResponseEntity<List<Module>> getMethodName(@RequestParam Integer code) {
        UserProfile profile = userProfileService.getUserProfileById(code).get();
        return ResponseEntity.ok().body(profile.getModule());
    } */
    

    @GetMapping("/search")
    public ResponseEntity<List<UserProfile>> search(@RequestParam("word") String searchWord) {
        List<UserProfile> profiles = userProfileService.search(searchWord);
        return ResponseEntity.ok(profiles);
    }

}
