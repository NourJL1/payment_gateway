package com.service;

import java.util.List;

import com.model.Module;

public interface ModuleService {

    List<com.model.Module> getAll();
    Module getById(Integer code);
    Module getByIdentifier(String identifier);
    Module create(Module module);
    Module update(Module module);
    void delete(Integer code);
    List<Module> search(String searchWord);
}
