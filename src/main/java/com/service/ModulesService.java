package com.service;

import java.util.List;

import com.model.Modules;

public interface ModulesService {

     List<com.model.Modules> getAll();
    Modules getById(Integer code);
    Modules create(Modules module);
    Modules update(Modules module);
    void delete(Integer code);

}
