package com.example.contactmenagment.controllers.contactDTO;


import com.example.contactmenagment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;

    @NotBlank
    private String phonenumber;

    @NotNull
    private UUID contactTypeUID;
}
