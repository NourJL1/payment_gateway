package com.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Modules;
import com.repository.ModulesRepository;
import com.service.ModulesService;
@Service
public class ModulesServiceImp implements ModulesService {

    @Autowired
    private ModulesRepository moduleRepository;

     @Override
    public List<Modules> getAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Modules getById(Integer code) {
        return moduleRepository.findById(code).orElse(null);
    }

    @Override
    public Modules create(Modules module) {
        return moduleRepository.save(module);
    }

    @Override
    public Modules update(Modules module) {
        return moduleRepository.save(module);
    }

    @Override
    public void delete(Integer code) {
        moduleRepository.deleteById(code);
    }

    
    }
