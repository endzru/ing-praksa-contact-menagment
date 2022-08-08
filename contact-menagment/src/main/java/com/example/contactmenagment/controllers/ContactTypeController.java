package com.example.contactmenagment.controllers;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.services.implementation.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact-type")
public class ContactTypeController {
    private final ContactTypeService contactTypeService;
    @GetMapping
    @ResponseBody
    public List<ContactTypeResponseDTO> getAllContactTypes(){
        return contactTypeService.getAll();
    }
    @GetMapping("{uid}")
    @ResponseBody
    public ContactTypeResponseDTO getContactTypeById(@PathVariable UUID uid){
        return contactTypeService.getByUid(uid);
    }
    @PostMapping
    public void saveContactType(@Valid @RequestBody ContactTypeRequestDTO contactTypeRequestDTO){
        contactTypeService.save(contactTypeRequestDTO);
    }
    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<ContactTypeResponseDTO> getAllContactTypePagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<ContactTypeResponseDTO> list = contactTypeService.getAllContactTypesResponseDTOPages(offset, pageSize);
        return list;
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
