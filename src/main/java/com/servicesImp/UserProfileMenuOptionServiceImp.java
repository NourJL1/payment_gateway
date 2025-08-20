package com.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.MenuOption;
import com.model.UserProfile;
import com.model.UserProfileMenuOption;
import com.repository.MenuOptionRepository;
import com.repository.UserProfileMenuOptionRepository;
import com.repository.UserProfileRepository;
import com.service.UserProfileMenuOptionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserProfileMenuOptionServiceImp implements UserProfileMenuOptionService {

    @Autowired
    UserProfileMenuOptionRepository repository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    MenuOptionRepository menuOptionRepository;

    @Override
    public UserProfileMenuOption createProfileMenuOption(UserProfileMenuOption profileMenuOption) {
        return repository.save(profileMenuOption);
    }

    @Override
    public Optional<UserProfileMenuOption> getProfileMenuOptionById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<UserProfileMenuOption> getAllProfileMenuOptions() {
        return repository.findAll();
    }

    @Override
    public UserProfileMenuOption updateProfileMenuOption(Integer id, UserProfileMenuOption profileMenuOption) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setCanAccess(profileMenuOption.getCanAccess());
                    existing.setCanInsert(profileMenuOption.getCanInsert());
                    existing.setCanUpdate(profileMenuOption.getCanUpdate());
                    existing.setCanDelete(profileMenuOption.getCanDelete());
                    existing.setCanEdit(profileMenuOption.getCanEdit());
                    existing.setCanPrint(profileMenuOption.getCanPrint());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("ProfileMenuOption not found with id: " + id));
    }

    @Override
    public void deleteProfileMenuOption(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserProfileMenuOption updateProfileMenuOption(UserProfileMenuOption upmo) {

        UserProfile profile = userProfileRepository.findById(upmo.getProfile().getCode())
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found"));

        MenuOption menuOption = menuOptionRepository.findById(upmo.getMenuOption().getCode())
                .orElseThrow(() -> new EntityNotFoundException("MenuOption not found"));

        List<UserProfileMenuOption> existingPermissions = repository.findByProfileAndMenuOption(profile, menuOption);

        if (!existingPermissions.isEmpty()) {
            // Update first matching record (assuming unique constraint)
            UserProfileMenuOption existing = existingPermissions.get(0);
            existing.setCanAccess(upmo.getCanAccess());
            existing.setCanInsert(upmo.getCanInsert());
            existing.setCanUpdate(upmo.getCanUpdate());
            existing.setCanDelete(upmo.getCanDelete());
            existing.setCanEdit(upmo.getCanEdit());
            existing.setCanPrint(upmo.getCanPrint());
            return repository.save(existing);
        } else {
            // Create new if none exists
            upmo.setProfile(profile);
            upmo.setMenuOption(menuOption);
            return repository.save(upmo);
        }
    }

    @Override
    public List<UserProfileMenuOption> getByProfile(Integer profileCode) {
        UserProfile profile = userProfileRepository.findById(profileCode).get();
        return repository.findByProfile(profile);
    }

    @Override
    public List<UserProfileMenuOption> getByProfileAndModule(Integer profileCode, Integer moduleCode) {
        return repository.findByModuleAndProfile(moduleCode, profileCode);
    }

    /* @Override
    public List<UserProfileMenuOption> search(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.search(searchWord);
    } */

}
