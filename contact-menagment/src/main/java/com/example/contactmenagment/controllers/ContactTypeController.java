package com.example.contactmenagment.controllers;

import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.services.implementation.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact-type")
public class ContactTypeController {

    private final ContactTypeService contactTypeService;

    @GetMapping
    public ResponseEntity<List<ContactType>> getAllContactTypes(){
        return ResponseEntity.ok().body(contactTypeService.getAllContactTypes());
    }
    @GetMapping("{id}")
    public ResponseEntity<ContactType> getContactTypeById(@PathVariable Long id){
        return ResponseEntity.ok().body(contactTypeService.getContactTypeById(id));
    }
    @PostMapping
    public ResponseEntity<ContactType> saveContactType(@RequestBody ContactType c){
        return ResponseEntity.ok().body(contactTypeService.saveContactType(c));
    }
    @PutMapping
    public ResponseEntity<ContactType> updateContactType(@RequestBody ContactType c){
        return ResponseEntity.ok().body(contactTypeService.updateContactType(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactType> deleteContactTypeById(@PathVariable Long id){
        return ResponseEntity.ok().body(contactTypeService.deleteContactTypeById(id));
    }


}
