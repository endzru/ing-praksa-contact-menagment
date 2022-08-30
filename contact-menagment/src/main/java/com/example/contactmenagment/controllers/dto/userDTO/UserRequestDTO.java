package com.example.contactmenagment.controllers.dto.userDTO;

import com.example.contactmenagment.entity.validators.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequestDTO {
    @Email
    private String email;
    @NotBlank
    @Size(max = 30, message = "firstName can be up to 30 characters")
    private String firstName;
    @NotBlank
    @Size(max = 30, message = "lastName can be up to 30 characters")
    private String lastName;

    @NotBlank
    @ValidPassword
    private String password;

    @NotNull
    private UUID roleid;
}
