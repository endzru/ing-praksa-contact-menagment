package com.example.contactmenagment.services.interfaces;

import com.example.contactmenagment.entity.Role;

import java.util.List;

public interface RoleServiceInterface {

    List<Role> getAllRole();

    Role getRoleById(Long id);

    Role saveRole(Role c);

    Role updateRole(Role c);

    Role deleteRoleById(Long id);
}
