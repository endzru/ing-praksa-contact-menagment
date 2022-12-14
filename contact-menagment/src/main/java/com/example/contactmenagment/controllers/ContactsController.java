package com.example.contactmenagment.controllers;


import com.example.contactmenagment.controllers.controllersInterface.ContactsControllerInterface;
import com.example.contactmenagment.controllers.dto.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.dto.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.services.ContactFileImportService;
import com.example.contactmenagment.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("authentication.principal.status == 'VERIFIED'")
public class ContactsController implements ContactsControllerInterface {
    private final ContactService contactService;
    private final ContactFileImportService contactFileImportService;

    @GetMapping
    @ResponseBody

    public Page<ContactResponseDTO> getAllContacts(Pageable pageable) {
        return contactService.getAll(pageable);
    }

    @GetMapping("/{contactUid}")
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> getContactByUid(@PathVariable UUID contactUid) {
        return ResponseEntity.ok().body(contactService.getDTOByUid(contactUid, contactService.getLoggedInUser().getUid()));
    }

    @PostMapping()
    public ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactDTO) {
        contactService.saveContact(contactDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{contactUid}")
    @ResponseBody
    public void updateContact(@PathVariable UUID contactUid, @RequestBody ContactRequestDTO contactRequestDTO) {
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
        return contactFileImportService.importContactsFromFile(csvfajl);
    }


}