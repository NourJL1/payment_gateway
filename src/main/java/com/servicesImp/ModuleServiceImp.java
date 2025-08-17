package com.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.MenuOption;
import com.model.Module;
import com.repository.ModuleRepository;
import com.service.ModuleService;

@Service
public class ModuleServiceImp implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> getAll() {
        return moduleRepository.findAll(Sort.by(Sort.Direction.ASC, "order"));
    }

    @Override
    public Module getById(Integer code) {
        return moduleRepository.findById(code).orElse(null);
    }

    @Override
    public Module create(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Module update(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void delete(Integer code) {
        moduleRepository.deleteById(code);
    }

    @Override
    public List<Module> search(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return moduleRepository.findAll();
        }
        return moduleRepository.search(searchWord);
    }

    @Override
    public Module getByIdentifier(String identifier) {
        return moduleRepository.findByIdentifier(identifier);
    }

}
