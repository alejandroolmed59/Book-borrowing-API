package com.olmedo.bookborrowing.repository;

import com.olmedo.bookborrowing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleId(Integer id);
    Role deleteRoleByRoleId(Integer id);
}
