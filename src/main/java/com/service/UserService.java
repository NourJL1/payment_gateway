package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
    List<User> getAll();

    User getById(Integer code);

    User create(User user);

    User update(User user);

    void delete(Integer code);
    long getUserCount(); // Add this method

}
