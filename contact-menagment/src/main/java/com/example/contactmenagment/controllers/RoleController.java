package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.services.implementation.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.getAll();
    }
    @GetMapping("/{uid}")
    @ResponseBody
    public Role getRoleById(@PathVariable UUID uid) {
        return roleService.getByUid(uid);
    }
    @PostMapping
    @ResponseBody
    public Role saveRole(@RequestBody Role role){
        role.setUid(UUID. randomUUID());
        return roleService.save(role);
    }

    @PutMapping
    @ResponseBody
    public Role updateRole(@RequestBody Role role){
        return roleService.save(role);
    }
    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteRoleByUid(@PathVariable UUID uid){
         roleService.deleteByUid(uid);
    }
}