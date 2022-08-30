package com.example.contactmenagment.controllers.dto.contactTypeDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactTypeRequestDTO {
    @NotBlank
    private String contactTypeName;
}
