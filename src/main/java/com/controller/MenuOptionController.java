package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.MenuOption;
import com.model.Module;
import com.service.MenuOptionService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/menu-options")
public class MenuOptionController {

    @Autowired
    MenuOptionService menuOptionService;

    // Create
    @PostMapping
    public ResponseEntity<MenuOption> createMenuOption(@RequestBody MenuOption menuOption) {
        MenuOption createdOption = menuOptionService.createMenuOption(menuOption);
        return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<MenuOption> getMenuOptionById(@PathVariable Integer id) {
        return menuOptionService.getMenuOptionById(id)
                .map(option -> new ResponseEntity<>(option, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getByIdentifier/{identifier}")
    public ResponseEntity<MenuOption>  getByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.ok().body(menuOptionService.getByIdentifier(identifier)) ;
    }

    @GetMapping
    public ResponseEntity<List<MenuOption>> getAllMenuOptions() {
        List<MenuOption> options = menuOptionService.getAllMenuOptions();
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @GetMapping("/by-module/{moduleCode}")
    public ResponseEntity<List<MenuOption>> getMenuOptionsByModule(
            @PathVariable Integer moduleCode) {
        List<MenuOption> options = menuOptionService.getMenuOptionsByModule(moduleCode);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<MenuOption> updateMenuOption(
            @PathVariable Integer id,
            @RequestBody MenuOption menuOption) {
        try {
            MenuOption updatedOption = menuOptionService.updateMenuOption(id, menuOption);
            return new ResponseEntity<>(updatedOption, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{menuOptionId}/change-module/{newModuleId}")
    public ResponseEntity<MenuOption> changeMenuOptionModule(
            @PathVariable Integer menuOptionId,
            @PathVariable Integer newModuleId) {
        try {
            MenuOption updatedOption = menuOptionService.changeMenuOptionModule(menuOptionId, newModuleId);
            return new ResponseEntity<>(updatedOption, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuOption(@PathVariable Integer id) {
        menuOptionService.deleteMenuOption(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuOption>> search(@RequestParam("word") String searchWord) {
        List<MenuOption> options = menuOptionService.search(searchWord);
        return ResponseEntity.ok(options);
    }
}
