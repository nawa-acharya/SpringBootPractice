package com.example.bootpractice.controller;

import com.example.bootpractice.model.User;
import com.example.bootpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nawa
 * on 9/21/20.
 * (c)Marathon Computer Systems
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public UserController(UserService userService, RestTemplateBuilder restTemplateBuilder) {
        this.userService = userService;
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        if (userList == null) userList = new ArrayList<>();
        userList.forEach(user -> user.setUserHistoryList(new ArrayList<>()));
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findUser(@PathVariable("id") Long id) {
        return userService.findUser(id);
    }

    @GetMapping(value = "/findByName/{fName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> findUser(@PathVariable("fName") String name) {
        List<User> userList = userService.findAllUsers();
        User finalUser = null;
        if (userList != null)
            finalUser =
                    userList
                            .stream()
                            .filter(user -> user != null && user.getFirstName() != null && user.getFirstName().equals(name))
                            .findFirst()
                            .orElse(null);
        if (finalUser == null) {
            finalUser = new User();
            finalUser.setFirstName("NULL");
        }
        return new ResponseEntity<User>(finalUser, HttpStatus.FOUND);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("{/id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean removeUser(@RequestBody User user) {
        return userService.removeUser(user);
    }
}
