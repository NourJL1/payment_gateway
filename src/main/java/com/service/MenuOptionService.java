package com.service;

import java.util.List;
import java.util.Optional;

import com.model.MenuOption;

public interface MenuOptionService {

    MenuOption createMenuOption(MenuOption menuOption);
    Optional<MenuOption> getMenuOptionById(Integer id);
    //Optional<MenuOption> getMenuOptionByIdentifier(String identifier);
    List<MenuOption> getAllMenuOptions();
    List<MenuOption> getMenuOptionsByModule(Integer moduleCode);
    MenuOption getByIdentifier(String identifier);
    MenuOption updateMenuOption(Integer id, MenuOption menuOption);
    void deleteMenuOption(Integer id);

    List<MenuOption> getChildOptions(Integer parentId);

    List<MenuOption> getMenuOptionsByModule(String moduleName);
    List<MenuOption> search(String searchWord);

    //List<MenuOption> getMenuOptionsByProfile(Integer profileId);
    //List<MenuOption> getAccessibleMenuOptions(Integer profileId);

}