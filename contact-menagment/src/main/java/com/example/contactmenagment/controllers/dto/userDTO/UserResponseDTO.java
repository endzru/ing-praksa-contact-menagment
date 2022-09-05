package com.example.contactmenagment.controllers.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID uid;
    private String email;
    private String firstName;
    private String lastName;
    private String phonenumber;
    private String status;
}
