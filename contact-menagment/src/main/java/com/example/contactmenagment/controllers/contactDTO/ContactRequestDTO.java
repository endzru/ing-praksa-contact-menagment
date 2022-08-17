package com.example.contactmenagment.controllers.contactDTO;


import com.opencsv.bean.CsvBindByName;
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
    @NotBlank
    @CsvBindByName
    private String firstName;

    @NotBlank
    @CsvBindByName
    private String lastName;

    @Email
    @CsvBindByName
    private String email;

    @NotBlank
    @CsvBindByName
    private String phonenumber;

    @CsvBindByName
    private UUID contactTypeUID;
}
