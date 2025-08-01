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
        UserProfileMenuOption result = userProfileMenuOptionService.setProfileMenuOption(profileMenuOption);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Update Permissions
/*     @PatchMapping("/{id}/permissions")
    public ResponseEntity<UserProfileMenuOption> updatePermissions(
            @PathVariable Integer id,
            @RequestBody PermissionUpdateRequest request) {
        try {
            UserProfileMenuOption updated = userProfileMenuOptionService.updatePermissions(
                    id,
                    request.getCanAccess(),
                    request.getCanInsert(),
                    request.getCanUpdate(),
                    request.getCanDelete(),
                    request.getCanEdit(),
                    request.getCanPrint()
            );
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } */

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileMenuOption(@PathVariable Integer id) {
        userProfileMenuOptionService.deleteProfileMenuOption(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Permission Checks
    @GetMapping("/check-access/{profileId}/{menuOptionId}")
    public ResponseEntity<Boolean> hasAccessPermission(
            @PathVariable Integer profileId,
            @PathVariable Integer menuOptionId) {
        boolean hasPermission = userProfileMenuOptionService.hasAccessPermission(profileId, menuOptionId);
        return new ResponseEntity<>(hasPermission, HttpStatus.OK);
    }

    // Add similar endpoints for other permission checks...
    @GetMapping("/check-insert/{profileId}/{menuOptionId}")
    public ResponseEntity<Boolean> hasInsertPermission(
            @PathVariable Integer profileId,
            @PathVariable Integer menuOptionId) {
        boolean hasPermission = userProfileMenuOptionService.hasInsertPermission(profileId, menuOptionId);
        return new ResponseEntity<>(hasPermission, HttpStatus.OK);
    }

    /* @GetMapping("/search")
    public ResponseEntity<List<UserProfileMenuOption>> search(@RequestParam("word") String searchWord) {
        List<UserProfileMenuOption> upmos = userProfileMenuOptionService.search(searchWord);
        return ResponseEntity.ok(upmos);
    } */

    // DTO for permission updates
    /* public static class PermissionUpdateRequest {
        private Boolean canAccess;
        private Boolean canInsert;
        private Boolean canUpdate;
        private Boolean canDelete;
        private Boolean canEdit;
        private Boolean canPrint;

        // Getters and setters
        public Boolean getCanAccess() { return canAccess; }
        public void setCanAccess(Boolean canAccess) { this.canAccess = canAccess; }
        public Boolean getCanInsert() { return canInsert; }
        public void setCanInsert(Boolean canInsert) { this.canInsert = canInsert; }
        public Boolean getCanUpdate() { return canUpdate; }
        public void setCanUpdate(Boolean canUpdate) { this.canUpdate = canUpdate; }
        public Boolean getCanDelete() { return canDelete; }
        public void setCanDelete(Boolean canDelete) { this.canDelete = canDelete; }
        public Boolean getCanEdit() { return canEdit; }
        public void setCanEdit(Boolean canEdit) { this.canEdit = canEdit; }
        public Boolean getCanPrint() { return canPrint; }
        public void setCanPrint(Boolean canPrint) { this.canPrint = canPrint; }
    }
 */
}
