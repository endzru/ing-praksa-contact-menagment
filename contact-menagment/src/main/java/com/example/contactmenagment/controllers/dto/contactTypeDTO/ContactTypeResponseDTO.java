package com.example.contactmenagment.controllers.dto.contactTypeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactTypeResponseDTO {
    private UUID uid;
    private String contactTypeName;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
