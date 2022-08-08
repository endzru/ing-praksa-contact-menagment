package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.services.implementation.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactsController {
    private final ContactsService contactsServce;

    @GetMapping
    @ResponseBody
    public List<ContactResponseDTO> getAllContacts(){
        return contactsServce.getAll();
    }
    @GetMapping("/{uid}")
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID uid){
        return ResponseEntity.ok().body(contactsServce.getDTOByUid(uid));
    }

    @PostMapping("/{uid}")
    public ResponseEntity saveContact(@Valid @PathVariable UUID uid , @RequestBody ContactRequestDTO contactDTO){
        contactsServce.saveContact(uid, contactDTO);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{uid}")
    @ResponseBody
    public void updateContact(@Valid @PathVariable UUID uid, @RequestBody ContactRequestDTO c){
        contactsServce.updateContact(uid, c);
    }
    @DeleteMapping("{uid}")
    @ResponseBody
    public void deleteContactById(@PathVariable UUID uid){
         contactsServce.deleteByUid(uid);
    }



}
