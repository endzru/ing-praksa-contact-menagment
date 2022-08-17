package com.example.contactmenagment.controllers;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.services.implementation.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("/admin/search")
@RequiredArgsConstructor
@Validated
public class AdminAccessories {
    private final ContactsService contactService;

    @GetMapping("/contacts/{field}")
    public Page<ContactResponseDTO> searchContacts(@PathVariable
                                                   @Size(min = 3, message = "Search field must contain at least 3 characters")
                                                   String field, Pageable pageable) {
        return contactService.searchContacts(field, pageable);
    }


}
