package com.servicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Modules;
import com.model.UserProfile;
import com.repository.ModulesRepository;
import com.repository.UserProfileRepository;
import com.service.UserProfileService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserProfileServiceImp implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ModulesRepository modulesRepository;

    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> getUserProfileById(Integer id) {
        return userProfileRepository.findById(id);
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile updateUserProfile(Integer id, UserProfile updatedProfile) {
        return userProfileRepository.findById(id)
                .map(existingProfile -> {
                    existingProfile.setIdentifier(updatedProfile.getIdentifier());
                    existingProfile.setLabel(updatedProfile.getLabel());
                    existingProfile.setViewBank(updatedProfile.getViewBank());
                    existingProfile.setViewBranch(updatedProfile.getViewBranch());
                    existingProfile.setViewChild(updatedProfile.getViewChild());
                    existingProfile.setViewCustomerMerchant(updatedProfile.getViewCustomerMerchant());
                    existingProfile.setGrantPermission(updatedProfile.getGrantPermission());
                    existingProfile.setCanDecryptPan(updatedProfile.getCanDecryptPan());
                    return userProfileRepository.save(existingProfile);
                })
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found with id: " + id));
    }

    @Override
    public void deleteUserProfile(Integer id) {
        userProfileRepository.deleteById(id);
    }

    @Override
    public List<UserProfile> getUserProfilesWithAccessToModule(Integer moduleCode) {
        return new ArrayList<>();//userProfileRepository.findByModulesCode(moduleCode);
    }

    @Override
    public boolean hasPermissionToViewCustomerMerchant(Integer profileId) {
        return userProfileRepository.findById(profileId)
                .map(UserProfile::getViewCustomerMerchant)
                .orElse(false);
    }

    @Override
    public boolean canUserProfileDecryptPan(Integer profileId) {
        return userProfileRepository.findById(profileId)
                .map(UserProfile::getCanDecryptPan)
                .orElse(false);
    }

    @Override
    public UserProfile addModuleToProfile(Integer profileId, Integer moduleId) {
        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found"));
        
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found"));
        
        if (!profile.getModules().contains(module)) {
            profile.getModules().add(module);
            return userProfileRepository.save(profile);
        }
        return profile;
    }

    @Override
    public UserProfile removeModuleFromProfile(Integer profileId, Integer moduleId) {
        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found"));
        
        profile.getModules().removeIf(module -> module.getCode().equals(moduleId));
        return userProfileRepository.save(profile);
    }

    @Override
    public List<UserProfile> search(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return userProfileRepository.findAll();
        }
        return userProfileRepository.search(searchWord);
    }

}
