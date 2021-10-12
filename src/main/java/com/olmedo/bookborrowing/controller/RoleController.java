package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.Role;
import com.olmedo.bookborrowing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
            throw new Exception("Rol no creado");
        } else {
            return createdRole;
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Role newValuesRole, @PathVariable Integer id) throws Exception {
        Role foundRole = roleService.findById(id);

        if (foundRole == null) {
            throw new Exception("Role not found with id=" + id);
        } else {
            foundRole = roleService.update(foundRole, newValuesRole);
            return ResponseEntity.ok(foundRole);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        Role foundRole = roleService.findById(id);

        if (foundRole == null) {
            throw new Exception("Role not found with id=" + id);
        } else {
            roleService.delete(foundRole);
            return ResponseEntity.ok().body("Eliminado");
        }
    }
}
