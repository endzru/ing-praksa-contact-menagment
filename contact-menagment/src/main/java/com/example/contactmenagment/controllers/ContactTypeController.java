package com.example.contactmenagment.controllers;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.services.implementation.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/contact-types")
public class ContactTypeController {
    private final ContactTypeService contactTypeService;

    @GetMapping("/{uid}")
    @ResponseBody
    public ContactTypeResponseDTO getContactTypeById(@PathVariable UUID uid){
        return contactTypeService.getByUid(uid);
    }
    @PostMapping
    public void saveContactType(@Valid @RequestBody ContactTypeRequestDTO contactTypeRequestDTO){
        contactTypeService.save(contactTypeRequestDTO);
    }
    @GetMapping
    public Page<ContactTypeResponseDTO> getAllContactTypePagination(Pageable pageable){
        return contactTypeService.getAllContactTypesResponseDTOPages(pageable);
    }

    @PutMapping("/{uid}")
    public void updateContactType(@Valid @PathVariable UUID uid, @RequestBody ContactTypeRequestDTO c){
        contactTypeService.updateContact(uid, c);
    }
    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteContactTypeById(@PathVariable UUID uid){
        contactTypeService.deleteByUid(uid);
    }

}
