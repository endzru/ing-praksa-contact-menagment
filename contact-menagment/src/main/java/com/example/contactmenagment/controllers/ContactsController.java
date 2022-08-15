package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.implementation.ContactsService;
import com.example.contactmenagment.services.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/contacts")
public class ContactsController {
    private final ContactsService contactsService;
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping()
    @ResponseBody
    public Page<ContactResponseDTO> getAllContacts(Pageable pageable){
        return contactsService.getAll(pageable);
    }
    @GetMapping("/{contactUid}")
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID contactUid){
        return ResponseEntity.ok().body(contactsService.getDTOByUid(contactUid, contactsService.getLoggedInUser().getUid()));
    }

//    @GetMapping("/{uid}/contacts")
//    public ResponseEntity<Page<ContactResponseDTO>> getAllContactsByUserUid(@PathVariable  UUID uid, Pageable pageable){
//        if(getAccess(uid)){
//            return ResponseEntity.ok().body(userService.getAllContactsByUserUid(uid, pageable));
//        }
//        return ResponseEntity.badRequest().build();
//    }

    @PostMapping()
    public ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactDTO){
        contactsService.saveContact(contactDTO);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{contactUid}")
    @ResponseBody
    public void updateContact(@Valid @PathVariable UUID contactUid, @RequestBody ContactRequestDTO contactRequestDTO) throws AccessDeniedException {
        contactsService.updateContact(contactUid, contactRequestDTO);
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
