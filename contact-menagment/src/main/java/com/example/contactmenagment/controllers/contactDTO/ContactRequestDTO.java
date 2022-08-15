package com.example.contactmenagment.controllers.contactDTO;


import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    @CsvBindByName
    @NotBlank
    private String firstName;
    @CsvBindByName
    @NotBlank
    private String lastName;
    @CsvBindByName
    @Email
    private String email;
    @CsvBindByName
    @NotBlank
    private String phonenumber;
    @CsvBindByName
    @NotNull
    private UUID contactTypeUID;
}
