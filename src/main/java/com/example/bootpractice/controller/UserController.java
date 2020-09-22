package com.example.bootpractice.controller;

import com.example.bootpractice.model.User;
import com.example.bootpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nawa
 * on 9/21/20.
 * (c)Marathon Computer Systems
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/find/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findUser(@PathVariable("id") Long id) {
        return userService.findUser(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean removeUser(@RequestBody User user) {
        return userService.removeUser(user);
    }
}
