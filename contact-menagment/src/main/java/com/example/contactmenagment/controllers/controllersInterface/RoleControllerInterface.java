package com.example.contactmenagment.controllers.controllersInterface;

import com.example.contactmenagment.entity.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface RoleControllerInterface extends GlobalApiResponseSetup{
    @Tag(name="Get All Roles.")
    List<Role> getAllRoles();

    @Tag(name="Get one Role.", description = "Get one Role by UID.")
    Role getRoleById(@PathVariable UUID roleUid);

    @Tag(name="Save Role.")
    Role saveRole(@RequestBody Role role);

    @Tag(name="Update Role.", description = "Update role by passing his UID, you can only change roleName.")
    Role updateRole(@PathVariable UUID roleUid, @RequestBody Role role);

    @Tag(name="Delete Role.", description = "Delete Role by passing UID.")
    void deleteRoleByUid(@PathVariable UUID roleUid);
}
