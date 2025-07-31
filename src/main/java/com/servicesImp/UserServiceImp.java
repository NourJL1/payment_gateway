package com.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;


@Service
public class UserServiceImp  implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer code) {
        return userRepository.findById(code).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer code) {
        userRepository.deleteById(code);
    }

    @Override
    public List<User> search(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.search(searchWord);
    }
}
 

