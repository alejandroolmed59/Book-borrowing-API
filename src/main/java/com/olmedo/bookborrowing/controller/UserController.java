package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.User;
import com.olmedo.bookborrowing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            throw new Exception("BedType not created");
        } else {
            return createdUser;
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody User newValues, @PathVariable String id) throws Exception {
        User found = userService.findById(id);

        if (found == null) {
            throw new Exception("Role not found with id=" + id);
        } else {
            found = userService.update(found, newValues);
            return ResponseEntity.ok(found);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        User found = userService.findById(id);

        if (found == null) {
            throw new Exception("Role not found with id=" + id);
        } else {
            userService.delete(found);
            return ResponseEntity.ok().body("Eliminado");
        }
    }

}
