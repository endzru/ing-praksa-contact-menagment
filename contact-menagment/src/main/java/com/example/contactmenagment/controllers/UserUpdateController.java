package com.example.contactmenagment.controllers;

import com.example.contactmenagment.controllers.dto.userDTO.UserRequestDTO;
import com.example.contactmenagment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserUpdateController {

    private UserService userService;

    @Autowired
    public UserUpdateController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public void updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        userService.profileInformationUpdate(userRequestDTO);
    }




}
