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

    @GetMapping("/{contactTypeUid}")
    @ResponseBody
    public ContactTypeResponseDTO getContactTypeById(@PathVariable UUID contactTypeUid){
        return contactTypeService.getContactTypeDTOByUid(contactTypeUid);
    }
    @PostMapping
    public void saveContactType(@Valid @RequestBody ContactTypeRequestDTO contactTypeRequestDTO){
        contactTypeService.save(contactTypeRequestDTO);
    }
    @GetMapping
    public Page<ContactTypeResponseDTO> getAllContactTypePagination(Pageable pageable){
        return contactTypeService.getAllContactTypesResponseDTOPages(pageable);
    }

    @PutMapping("/{contactTypeUid}")
    public void updateContactType(@Valid @PathVariable UUID contactTypeUid, @RequestBody ContactTypeRequestDTO c){
        contactTypeService.updateContactType(contactTypeUid, c);
    }
    @DeleteMapping("/{contactTypeUid}")
    @ResponseBody
    public void deleteContactTypeById(@PathVariable UUID contactTypeUid){
        contactTypeService.deleteByUid(contactTypeUid);
    }

}
