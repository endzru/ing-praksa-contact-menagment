package com.example.contactmenagment.controllers;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.controllers.controllersInterface.ContactTypeControllerInterface;
import com.example.contactmenagment.services.implementation.ContactTypeService;
//import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/contact-types")
@Validated
public class ContactTypeController implements ContactTypeControllerInterface {
    private final ContactTypeService contactTypeService;

    @GetMapping("/{contactTypeUid}")
    @ResponseBody
    public ContactTypeResponseDTO getContactTypeByUid(@PathVariable UUID contactTypeUid){
        return contactTypeService.getContactTypeDTOByUid(contactTypeUid);
    }
    @PostMapping
    public void saveContactType(@RequestBody ContactTypeRequestDTO contactTypeRequestDTO){
        contactTypeService.save(contactTypeRequestDTO);
    }
    @GetMapping
    public Page<ContactTypeResponseDTO> getAllContactTypePagination(Pageable pageable){
        return contactTypeService.getAllContactTypesResponseDTOPages(pageable);
    }

    @PutMapping("/{contactTypeUid}")
    public void updateContactType(@PathVariable UUID contactTypeUid, @RequestBody ContactTypeRequestDTO c){
        contactTypeService.updateContactType(contactTypeUid, c);
    }
    @DeleteMapping("/{contactTypeUid}")
    @ResponseBody
    public void deleteContactTypeById(@PathVariable UUID contactTypeUid){
        contactTypeService.deleteByUid(contactTypeUid);
    }

}
