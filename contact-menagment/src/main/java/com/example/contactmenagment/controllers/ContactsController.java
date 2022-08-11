package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.services.implementation.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactsService contactsService;

    @GetMapping()
    @ResponseBody
    public Page<ContactResponseDTO> getAllContacts(Pageable pageable){
        return contactsService.getAll(pageable);
    }
    @GetMapping("/{uid}")
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID uid){
        return ResponseEntity.ok().body(contactsService.getDTOByUid(uid));
    }

    @PostMapping("/{uid}")
    public ResponseEntity saveContact(@Valid @PathVariable UUID uid , @RequestBody ContactRequestDTO contactDTO){
        contactsService.saveContact(uid, contactDTO);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{uid}")
    @ResponseBody
    public void updateContact(@Valid @PathVariable UUID uid, @RequestBody ContactRequestDTO c){
        contactsService.updateContact(uid, c);
    }
    @DeleteMapping("{uid}")
    @ResponseBody
    public void deleteContactById(@PathVariable UUID uid){
         contactsService.deleteByUid(uid);
    }

    @GetMapping("/search/{field}")
    public List<ContactResponseDTO> searchContacts(@PathVariable String field){
        return contactsService.searchContacts(field);
    }


}
