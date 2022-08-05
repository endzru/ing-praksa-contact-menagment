package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleByUid(UUID uid);
    void deleteRoleByUid(UUID uid);
}