package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.services.serviceImplementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.findAllUser());
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable Integer idUser){
        return ResponseEntity.ok().body(userService.getUserById(idUser));
    }


}
