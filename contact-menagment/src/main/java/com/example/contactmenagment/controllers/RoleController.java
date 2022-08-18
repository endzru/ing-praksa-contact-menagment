package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.services.implementation.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.getAll();
    }
    @GetMapping("/{roleUid}")
    @ResponseBody
    public Role getRoleById(@PathVariable UUID roleUid) {
        return roleService.getByUid(roleUid);
    }
    @PostMapping
    @ResponseBody
    public Role saveRole(@RequestBody Role role){
        return roleService.save(role);
    }

    @PutMapping("/{roleUid}")
    @ResponseBody
    public Role updateRole(@PathVariable UUID roleUid, @RequestBody Role role){
        return roleService.updateRole(roleUid, role);
    }
    @DeleteMapping("/{roleUid}")
    @ResponseBody
    public void deleteRoleByUid(@PathVariable UUID roleUid){
         roleService.deleteByUid(roleUid);
    }
}
