package com.example.contactmenagment.services.serviceInterface;

import com.example.contactmenagment.entity.Role;

import java.util.List;

public interface RoleServiceInterface {

    //GET
    List<Role> findAllRoles();
    Role getRoleById(Integer id);

    //POST
    Role saveRole(Role r);

    //PUT
    Role updateRole(Role r);

    //DELETE
    void deleteRoleById(Integer id);


}
