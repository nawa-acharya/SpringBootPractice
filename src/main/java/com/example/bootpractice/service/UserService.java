package com.example.bootpractice.service;

import com.example.bootpractice.model.User;
import com.example.bootpractice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nawa
 * on 9/21/20.
 * (c)Marathon Computer Systems
 */

@Service
public class UserService {
    private final UserRepository userRepository;

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public boolean removeUser(User user) {
        if (user != null) {
            userRepository.delete(user);
            return userRepository.findById(user.getId()).isPresent();
        }
        return false;
    }

    public User save(User user){
       return userRepository.save(user);
    }
}
