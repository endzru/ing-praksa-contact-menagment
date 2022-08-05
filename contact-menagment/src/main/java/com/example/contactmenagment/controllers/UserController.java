package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    @GetMapping
    @ResponseBody
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{uid}")
    @ResponseBody
    public UserResponseDTO getUserByUid(@PathVariable UUID uid ){
        return userService.getByUid(uid);
    }

    @GetMapping("/{uid}/contacts")
    public List<ContactResponseDTO> getAllContactsByUserUid(@PathVariable  UUID uid){
        return userService.getAllContactsByUserUid(uid);
    }

    @PostMapping
    @ResponseBody
    public void saveUser(@RequestBody UserRequestDTO s){
        userService.save(s);
    }

//    @PutMapping
//    @ResponseBody
//    public User updateUser(@RequestBody User s){
//        return userService.save(s);
//    }
    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteUserByUid(@PathVariable UUID uid){
        userService.deleteByUid(uid);
    }

}
