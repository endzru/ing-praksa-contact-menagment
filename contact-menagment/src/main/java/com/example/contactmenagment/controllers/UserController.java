package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseBody
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/{userUid}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID userUid) {
        return userService.getUserDTOByUid(userUid);
    }

    @PostMapping
    @ResponseBody
    public void saveUser(@Valid @RequestBody UserRequestDTO s) {
        userService.save(s);
    }

    @PutMapping("/{userUid}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @PathVariable UUID userUid, @RequestBody UserRequestDTO user) {
        userService.updateUser(user, userUid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userUid}")
    @ResponseBody
    public void deleteUserByUid(@PathVariable UUID userUid) {
        userService.deleteByUid(userUid);
    }
}
