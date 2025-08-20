package com.service;

import java.util.List;
import java.util.Optional;

import com.model.UserProfile;
import com.model.UserProfileMenuOption;

public interface UserProfileMenuOptionService {

    // Basic CRUD Operations
    UserProfileMenuOption createProfileMenuOption(UserProfileMenuOption profileMenuOption);
    Optional<UserProfileMenuOption> getProfileMenuOptionById(Integer id);
    List<UserProfileMenuOption> getAllProfileMenuOptions();
    List<UserProfileMenuOption> getByProfileAndModule(Integer profileCode, Integer moduleCode);
    UserProfileMenuOption updateProfileMenuOption(Integer id, UserProfileMenuOption profileMenuOption);
    void deleteProfileMenuOption(Integer id);

    UserProfileMenuOption updateProfileMenuOption(UserProfileMenuOption upmo);
    List<UserProfileMenuOption> getByProfile(Integer profileCode);
    //List<UserProfileMenuOption> getByMenuOption(Integer menuOptionId);

    

    //List<UserProfileMenuOption> search(String searchWord);

}
