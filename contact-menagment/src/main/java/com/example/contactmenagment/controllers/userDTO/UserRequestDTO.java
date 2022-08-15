package com.example.contactmenagment.controllers.userDTO;

import com.example.contactmenagment.entity.validators.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    @ValidPassword
    private String password;

    @NotNull
    private UUID roleid;
}
