package com.service;

import java.util.List;
import java.util.Optional;

import com.model.MenuOption;

public interface MenuOptionService {

    // Basic CRUD Operations
    MenuOption createMenuOption(MenuOption menuOption);
    Optional<MenuOption> getMenuOptionById(Integer id);
    //Optional<MenuOption> getMenuOptionByIdentifier(String identifier);
    List<MenuOption> getAllMenuOptions();
    List<MenuOption> getMenuOptionsByModule(Integer moduleCode);
    MenuOption updateMenuOption(Integer id, MenuOption menuOption);
    void deleteMenuOption(Integer id);

    // Hierarchy Operations
    //List<MenuOption> getChildOptions(Integer parentId);
    //MenuOption updateParentOption(Integer childId, Integer newParentId);
    //List<MenuOption> getRootMenuOptions(); // Options without parent

    // Module-related Operations
    MenuOption changeMenuOptionModule(Integer menuOptionId, Integer newModuleId);
    //List<MenuOption> getMenuOptionsByModuleName(String moduleName);
    List<MenuOption> search(String searchWord);

    // Profile-MenuOption Relationship Operations
    //List<MenuOption> getMenuOptionsByProfile(Integer profileId);
    //List<MenuOption> getAccessibleMenuOptions(Integer profileId);

    // Form Operations
    //Optional<MenuOption> getMenuOptionByFormName(String formName);

    //List<MenuOption> search(String word);

    // Bulk Operations
    //List<MenuOption> createMultipleMenuOptions(List<MenuOption> menuOptions);
    //void deleteAllMenuOptionsByModule(Integer moduleId);
}