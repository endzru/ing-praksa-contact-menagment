package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{uid}")
    @ResponseBody
    public User getUserById(@PathVariable UUID uid ){
        return userService.getByUid(uid);
    }

    @PostMapping
    @ResponseBody
    public User saveUser(@RequestBody User s){
        s.setUid(UUID. randomUUID());
        return userService.save(s);
    }

    @PutMapping
    @ResponseBody
    public User updateUser(@RequestBody User s){
        return userService.save(s);
    }

    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteUserByUid(@PathVariable UUID uid){
        userService.deleteByUid(uid);
    }

}
