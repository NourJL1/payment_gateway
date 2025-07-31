package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.MenuOption;
import com.model.Modules;

import java.util.List;
import com.model.UserProfileMenuOption;



public interface MenuOptionRepository extends JpaRepository<MenuOption, Integer> {

    List<MenuOption> findByModule(Modules module);
    List<MenuOption> findByProfileMenuOptions(List<UserProfileMenuOption> profileMenuOptions);
    
    @Query("SELECT mo FROM MenuOption mo WHERE " +
	           "LOWER(mo.identifier) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           "LOWER(mo.label) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
	           //"CAST(mo.parentOption AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
	           "CAST(mo.formName AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<MenuOption> search(String searchWord);
    
}
