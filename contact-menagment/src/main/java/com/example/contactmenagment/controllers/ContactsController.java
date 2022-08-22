package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.services.implementation.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/contacts")
@Validated
public class ContactsController {
    private final ContactsService contactService;

    @GetMapping
    @ResponseBody
    public Page<ContactResponseDTO> getAllContacts(Pageable pageable) {
        return contactService.getAll(pageable);
    }

    @GetMapping("/{contactUid}")
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID contactUid) {
        return ResponseEntity.ok().body(contactService.getDTOByUid(contactUid, contactService.getLoggedInUser().getUid()));
    }

    @PostMapping()
    public ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactDTO) {
        contactService.saveContact(contactDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{contactUid}")
    @ResponseBody
    public void updateContact(@Valid @PathVariable UUID contactUid, @RequestBody ContactRequestDTO contactRequestDTO) {
        contactService.updateContact(contactUid, contactRequestDTO);
    }

    @DeleteMapping("{contactUid}")
    @ResponseBody
    public void deleteContactById(@PathVariable UUID contactUid) {
        contactService.deleteByUid(contactUid);
    }


    @GetMapping("/search/{field}")
    public Page<ContactResponseDTO> getContactsWithSearch(
            @PathVariable @Size(min = 3, message = "Search field must contain at least 3 characters.") String field,
            Pageable pageable) {
        return contactService.getAllByFieldAdmin(field, pageable);
    }

    @PostMapping("/file")
    public ResponseEntity readCSV(@Valid @RequestParam("csvfajl") MultipartFile csvfajl){
        return contactService.importContactsFromFile(csvfajl);
    }


}