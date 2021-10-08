package com.olmedo.bookborrowing.service;

import com.olmedo.bookborrowing.entity.Role;
import com.olmedo.bookborrowing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll(){return roleRepository.findAll();}

    public Role create(Role newRole){return roleRepository.save(newRole);}
}
