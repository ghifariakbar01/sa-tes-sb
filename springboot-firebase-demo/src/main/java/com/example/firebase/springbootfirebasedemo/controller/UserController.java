package com.example.firebase.springbootfirebasedemo.controller;

import com.example.firebase.springbootfirebasedemo.entity.Users;
import com.example.firebase.springbootfirebasedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String saveUser(@RequestBody Users user) throws ExecutionException {

        return userService.saveUser(user);
    }

    @GetMapping("/users/{username}")
    public Users getUser(@PathVariable String username) throws ExecutionException {

        return userService.getUserDetails(username);
    }

    @GetMapping("/users")
    public List<Users> getAll() throws ExecutionException, InterruptedException {

        return userService.getUsers();
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody Users user) throws ExecutionException {

        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) throws ExecutionException {

        return userService.deleteUser(username);
    }
}
