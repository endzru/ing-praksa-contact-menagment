package com.example.contactmenagment.controllers.userDTO;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID uid;
    private String email;
    private String firstName;
    private String lastName;
    private List<ContactResponseDTO> contacts;
}
