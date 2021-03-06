package com.olmedo.bookborrowing.service;


import com.olmedo.bookborrowing.entity.User;
import com.olmedo.bookborrowing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){return userRepository.findAll();}

    public User create(User newUser){return userRepository.save(newUser);}

    public User findById(String id){return userRepository.findByUserId(id);}

    public User update(User found, User newValues) {
        found.setName(newValues.getName());
        found.setEmail(newValues.getEmail());
        found.setPhoneNumber(newValues.getPhoneNumber());
        return userRepository.save(found);
    }
    public void delete(User found) {
        userRepository.delete(found);
    }
}
