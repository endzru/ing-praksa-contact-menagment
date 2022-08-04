package com.example.contactmenagment.controllers;

import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.services.implementation.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact-type")
public class ContactTypeController {
    private final ContactTypeService contactTypeService;
    @GetMapping
    @ResponseBody
    public List<ContactType> getAllContactTypes(){
        return contactTypeService.getAll();
    }
    @GetMapping("{uid}")
    @ResponseBody
    public ContactType getContactTypeById(@PathVariable UUID uid){
        return contactTypeService.getByUid(uid);
    }
    @PostMapping
    @ResponseBody
    public ContactType saveContactType(@RequestBody ContactType c){
        c.setUid(UUID. randomUUID());
        return contactTypeService.save(c);
    }
    @PutMapping
    public ContactType updateContactType(@RequestBody ContactType c){
        return contactTypeService.save(c);
    }
    @DeleteMapping("/{uid}")
    @ResponseBody
    public void deleteContactTypeById(@PathVariable UUID uid){
        contactTypeService.deleteByUid(uid);
    }


}
