package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.services.serviceImplementation.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok().body(roleService.findAllRoles());
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer idRole){
        return ResponseEntity.ok().body(roleService.getRoleById(idRole));
    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.saveRole(role));
    }

    @PutMapping
    public ResponseEntity<Role> update(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.updateRole(role));
    }
    @DeleteMapping("/{idRole}")
    public void deleteRoleById(@PathVariable Integer idRole){
        roleService.deleteRoleById(idRole);
    }

}
