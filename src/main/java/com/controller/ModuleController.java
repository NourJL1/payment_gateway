package com.controller;

import com.model.MenuOption;
import com.model.Module;
import com.model.UserProfile;
import com.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins = "*") // Optional: allows cross-origin if using Angular frontend
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // Get all Module
    @GetMapping
    public List<Module> getAllModule() {
        return moduleService.getAll();
    }

    // Get a module by its code
    @GetMapping("/{code}")
    public ResponseEntity<Module> getModuleById(@PathVariable Integer code) {
        Module module = moduleService.getById(code);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(module);
    }

    @GetMapping("/getByIdentifier/{identifier}")
    public ResponseEntity<Module>  getByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.ok().body(moduleService.getByIdentifier(identifier)) ;
    }

    // Create a new module
    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.create(module);
    }

    // Update an existing module
    @PutMapping("/{code}")
    public ResponseEntity<Module> updateModule(@PathVariable Integer code, @RequestBody Module moduleDetails) {
        Module existingModule = moduleService.getById(code);
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

        Module updated = moduleService.update(existingModule);
        return ResponseEntity.ok(updated);
    }

    // Delete a module
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteModule(@PathVariable Integer code) {
        Module existing = moduleService.getById(code);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        moduleService.delete(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Module>> search(@RequestParam("word") String searchWord) {
        List<Module> Module = moduleService.search(searchWord);
        return ResponseEntity.ok(Module);
    }
}
