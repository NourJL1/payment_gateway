package com.controller;

import com.model.MenuOption;
import com.model.Modules;
import com.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins = "*") // Optional: allows cross-origin if using Angular frontend
public class ModuleController {

    @Autowired
    private ModulesService moduleService;

    // Get all modules
    @GetMapping
    public List<Modules> getAllModules() {
        return moduleService.getAll();
    }

    // Get a module by its code
    @GetMapping("/{code}")
    public ResponseEntity<Modules> getModuleById(@PathVariable Integer code) {
        Modules module = moduleService.getById(code);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(module);
    }

    // Create a new module
    @PostMapping
    public Modules createModule(@RequestBody Modules module) {
        return moduleService.create(module);
    }

    // Update an existing module
    @PutMapping("/{code}")
    public ResponseEntity<Modules> updateModule(@PathVariable Integer code, @RequestBody Modules moduleDetails) {
        Modules existingModule = moduleService.getById(code);
        if (existingModule == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the fields
        existingModule.setIdentifier(moduleDetails.getIdentifier());
        existingModule.setLabel(moduleDetails.getLabel());
        existingModule.setLogo(moduleDetails.getLogo());
        existingModule.setIsMenu(moduleDetails.getIsMenu());
        existingModule.setAccessPath(moduleDetails.getAccessPath());
        existingModule.setOrder(moduleDetails.getOrder());
        existingModule.setParentModule(moduleDetails.getParentModule());
        existingModule.setProfiles(moduleDetails.getProfiles());
        existingModule.setMenuOptions(moduleDetails.getMenuOptions());

        Modules updated = moduleService.update(existingModule);
        return ResponseEntity.ok(updated);
    }

    // Delete a module
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteModule(@PathVariable Integer code) {
        Modules existing = moduleService.getById(code);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        moduleService.delete(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Modules>> search(@RequestParam("word") String searchWord) {
        List<Modules> modules = moduleService.search(searchWord);
        return ResponseEntity.ok(modules);
    }
}
