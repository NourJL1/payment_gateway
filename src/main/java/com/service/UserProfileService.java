package com.service;

import java.util.List;
import java.util.Optional;

import com.model.Modules;
import com.model.UserProfile;
import com.model.UserProfileMenuOption;

public interface UserProfileService {

    // Create
    UserProfile createUserProfile(UserProfile userProfile);
    
    // Read
    Optional<UserProfile> getUserProfileById(Integer id);
    //Optional<UserProfile> getUserProfileByIdentifier(String identifier);
    List<UserProfile> getAllUserProfiles();
    //List<UserProfile> getUserProfilesByViewBankPermission(boolean hasPermission);
    //List<UserProfile> getUserProfilesByGrantPermission(boolean hasPermission);
    
    // Update
    UserProfile updateUserProfile(Integer id, UserProfile userProfile);
    //UserProfile updateUserProfileModules(Integer id, List<Modules> modules);
    //UserProfile updateUserProfileMenuOptions(Integer id, List<UserProfileMenuOption> menuOptions);
    
    // Toggle Permissions
    //UserProfile toggleViewBankPermission(Integer id, boolean status);
    //UserProfile toggleViewBranchPermission(Integer id, boolean status);
    //UserProfile toggleGrantPermission(Integer id, boolean status);
    //UserProfile togglePanDecryptionPermission(Integer id, boolean status);
    
    // Delete
    void deleteUserProfile(Integer id);
    
    // Complex Queries
    List<UserProfile> getUserProfilesWithAccessToModule(Integer moduleCode);
    boolean hasPermissionToViewCustomerMerchant(Integer profileId);
    boolean canUserProfileDecryptPan(Integer profileId);
    
    // Relationship Management
    UserProfile addModuleToProfile(Integer profileId, Integer moduleId);
    UserProfile removeModuleFromProfile(Integer profileId, Integer moduleId);
    //UserProfile addMenuOptionToProfile(Integer profileId, UserProfileMenuOption menuOption);
    //UserProfile removeMenuOptionFromProfile(Integer profileId, Integer menuOptionId);

    List<UserProfile> search(String searchWord);
    
    // Bulk Operations
    //List<UserProfile> createMultipleUserProfiles(List<UserProfile> userProfiles);
    //int deactivateAllProfilesWithoutUsers();
}