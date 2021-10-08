package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.Role;
import com.olmedo.bookborrowing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Role> getRol() {
        List<Role> roles = roleService.findAll();
        return roles;
    }
    @PostMapping("/")
    public Role create(@RequestBody Role role) throws Exception {
        Role createdRole = roleService.create(role);

        if (createdRole==null) {
            throw new Exception("BedType were not created");
        } else {
            return createdRole;
        }
    }
}
