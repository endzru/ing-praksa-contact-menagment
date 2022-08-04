package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByUid(UUID uid);
    void deleteRoleByUid(UUID uid);
}