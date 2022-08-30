package com.example.contactmenagment.controllers.dto.contactDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {
    private UUID uid;
    private String firstName;
    private String lastName;
    private String email;
    private String phonenumber;
    private String contactType;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
