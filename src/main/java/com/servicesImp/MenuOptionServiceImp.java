package com.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.MenuOption;
import com.model.Module;
import com.model.UserProfile;
import com.repository.MenuOptionRepository;
import com.repository.ModuleRepository;
import com.repository.UserProfileRepository;
import com.service.MenuOptionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MenuOptionServiceImp implements MenuOptionService {

    @Autowired
    MenuOptionRepository menuOptionRepository;

    @Autowired
    ModuleRepository ModuleRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public MenuOption createMenuOption(MenuOption menuOption) {
        return menuOptionRepository.save(menuOption);
    }

    @Override
    public Optional<MenuOption> getMenuOptionById(Integer id) {
        return menuOptionRepository.findById(id);
    }

    @Override
    public List<MenuOption> getAllMenuOptions() {
        return menuOptionRepository.findAll();
    }

    @Override
    public List<MenuOption> getMenuOptionsByModule(Integer moduleCode) {
        Module module = ModuleRepository.findById(moduleCode).get();
        return menuOptionRepository.findByModule(module);
    }

    @Override
    public MenuOption updateMenuOption(Integer id, MenuOption menuOption) {
        return menuOptionRepository.findById(id)
                .map(existingOption -> {
                    existingOption.setIdentifier(menuOption.getIdentifier());
                    existingOption.setLabel(menuOption.getLabel());
                    existingOption.setParentOption(menuOption.getParentOption());
                    existingOption.setFormName(menuOption.getFormName());
                    existingOption.setModule(menuOption.getModule());
                    return menuOptionRepository.save(existingOption);
                })
                .orElseThrow(() -> new EntityNotFoundException("MenuOption not found with id: " + id));
    }

    @Override
    public void deleteMenuOption(Integer id) {
        menuOptionRepository.deleteById(id);
    }

    @Override
    public List<MenuOption> search(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return menuOptionRepository.findAll();
        }
        return menuOptionRepository.search(searchWord);
    }

    @Override
    public MenuOption getByIdentifier(String identifier) {
        return menuOptionRepository.findByIdentifier(identifier);
    }

    @Override
    public List<MenuOption> getChildOptions(Integer parentId) {
        MenuOption parent = menuOptionRepository.findById(parentId).get();
        return menuOptionRepository.findByParentOption(parent);//.findByParentOptionIsNotNull();
    }

    @Override
    public List<MenuOption> getMenuOptionsByModule(String moduleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMenuOptionsByModule'");
    }

}
