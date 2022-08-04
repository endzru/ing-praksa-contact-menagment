package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.services.implementation.RoleService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok().body(roleService.getAllRole());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.getRoleById(id));
    }
    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.saveRole(role));
    }

    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.updateRole(role));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRoleById(@PathVariable Long id){
      return ResponseEntity.ok().body(roleService.deleteRoleById(id));
    }
}
