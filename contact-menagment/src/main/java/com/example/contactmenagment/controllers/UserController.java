package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id ){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User s){
        return ResponseEntity.ok().body(userService.saveUser(s));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User s){
        return ResponseEntity.ok().body(userService.updateUser(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }

}
