package com.example.contactmenagment.controllers.contactDTO;


import com.example.contactmenagment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phonenumber;
    private UUID contactTypeUID;
}
