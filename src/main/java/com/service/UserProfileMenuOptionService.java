package com.service;

import java.util.List;
import java.util.Optional;

import com.model.UserProfileMenuOption;

public interface UserProfileMenuOptionService {

    // Basic CRUD Operations
    UserProfileMenuOption createProfileMenuOption(UserProfileMenuOption profileMenuOption);
    Optional<UserProfileMenuOption> getProfileMenuOptionById(Integer id);
    List<UserProfileMenuOption> getAllProfileMenuOptions();
    UserProfileMenuOption updateProfileMenuOption(Integer id, UserProfileMenuOption profileMenuOption);
    void deleteProfileMenuOption(Integer id);

    // Profile-MenuOption Relationship Management
    UserProfileMenuOption setProfileMenuOption(UserProfileMenuOption upmo);
    /* Optional<UserProfileMenuOption> getByProfileAndMenuOption(Integer profileId, Integer menuOptionId);
    List<UserProfileMenuOption> getByProfile(Integer profileId);
    List<UserProfileMenuOption> getByMenuOption(Integer menuOptionId); */

    // Permission Management
/*     UserProfileMenuOption updatePermissions(
        Integer id,
        Boolean canAccess,
        Boolean canInsert,
        Boolean canUpdate,
        Boolean canDelete,
        Boolean canEdit,
        Boolean canPrint
    ); */
    /* UserProfileMenuOption toggleAccessPermission(Integer id, Boolean status);
    UserProfileMenuOption toggleInsertPermission(Integer id, Boolean status);
    UserProfileMenuOption toggleUpdatePermission(Integer id, Boolean status);
    UserProfileMenuOption toggleDeletePermission(Integer id, Boolean status);
    UserProfileMenuOption toggleEditPermission(Integer id, Boolean status);
    UserProfileMenuOption togglePrintPermission(Integer id, Boolean status); */

    // Permission Checking
    boolean hasAccessPermission(Integer profileId, Integer menuOptionId);
    boolean hasInsertPermission(Integer profileId, Integer menuOptionId);
    boolean hasUpdatePermission(Integer profileId, Integer menuOptionId);
    boolean hasDeletePermission(Integer profileId, Integer menuOptionId);
    boolean hasEditPermission(Integer profileId, Integer menuOptionId);
    boolean hasPrintPermission(Integer profileId, Integer menuOptionId);

    //List<UserProfileMenuOption> search(String searchWord);

}
