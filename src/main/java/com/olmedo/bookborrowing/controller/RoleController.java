package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.Role;
import com.olmedo.bookborrowing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public List<Role> getRol() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
    @PostMapping("/")
    public Role create(@RequestBody Role role) throws Exception {
        Role createdRole = roleRepository.save(role);

        if (createdRole==null) {
            throw new Exception("BedType were not created");
        } else {
            return role;
        }
    }
}
