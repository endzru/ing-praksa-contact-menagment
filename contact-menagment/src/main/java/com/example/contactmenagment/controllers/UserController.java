package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.PrivateClassLoader;
import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping("")
    @ResponseBody
    public Page<UserResponseDTO> getAllUsers(Pageable pageable){
        return userService.getAll(pageable);
    }

    @GetMapping("/{uid}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID uid){

        return userService.getDTOByUid(uid);
    }

    @GetMapping("/{uid}/contacts")
    public ResponseEntity<Page<ContactResponseDTO>> getAllContactsByUserUid(@PathVariable  UUID uid, Pageable pageable){
        return userService.getAllContactsByUserUid(uid, pageable);
    }

    @PostMapping
    @ResponseBody
    public void saveUser(@Valid @RequestBody UserRequestDTO s){
        userService.save(s);
    }

    @PutMapping("/{uid}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @PathVariable UUID uid, @RequestBody UserRequestDTO user){
        return userService.updateUser(uid,user);
    }
    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteUserByUid(@PathVariable UUID uid){
        userService.deleteByUid(uid);
    }

}
