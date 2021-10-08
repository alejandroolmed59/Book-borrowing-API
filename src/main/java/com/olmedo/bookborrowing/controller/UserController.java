package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.User;
import com.olmedo.bookborrowing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){
        return userService.findById(id);
    }

    @PostMapping("/")
    public User create(@RequestBody User newUser) throws Exception {
        User createdUser = userService.create(newUser);

        if (createdUser==null) {
            throw new Exception("BedType were not created");
        } else {
            return createdUser;
        }
    }



}
