package com.example.contactmenagment.controllers.controllersInterface;

import com.example.contactmenagment.controllers.dto.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.dto.contactDTO.ContactResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;


public interface ContactsControllerInterface extends GlobalApiResponseSetup {

    @Tag(name="Get All Contacts", description = "Get all contacts for logged in user.")
    Page<ContactResponseDTO> getAllContacts(Pageable pageable);

    @Tag(name="Get Contact By Uid", description = "Get one Contact by passing his UID.")
    ResponseEntity<ContactResponseDTO> getContactByUid(@PathVariable UUID contactUid);

    @Tag(name="Save Contact", description = "Save contact for logged in user.")
    ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactDTO);

    @Tag(name="Update Contact", description = "Update contact, pass his UID and Send ContactRequestDTO, change the information you want!")
    void updateContact(@PathVariable UUID contactUid, @RequestBody ContactRequestDTO contactRequestDTO);

    @Tag(name="Delete Contact", description = "Delete a Contact by passing his UID.")
    void deleteContactById(@PathVariable UUID contactUid);

    @Tag(name="Search for Contact", description = "Get all contacts that match your input. ")
    Page<ContactResponseDTO> getContactsWithSearch(
            @PathVariable @Size(min = 3, message = "Search field must contain at least 3 characters.") String field,
            Pageable pageable);

    @Tag(name="Import Batch of Contacts", description = "Save contacts by sending a CSV file of ContactRequestDTO's")
    ResponseEntity readCSV(@Valid @RequestParam("csvfajl") MultipartFile csvfajl);

}
