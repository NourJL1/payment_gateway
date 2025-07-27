package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{code}")
    public User getById(@PathVariable Integer code) {
        return userService.getById(code);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{code}")
    public User update(@PathVariable Integer code, @RequestBody User user) {
        user.setCode(code); // Add this method in your User entity if missing
        return userService.update(user);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable Integer code) {
        userService.delete(code);
    }
    
    @GetMapping("/count")
    public long getUserCount() {
        return userService.getUserCount();
    }

}
