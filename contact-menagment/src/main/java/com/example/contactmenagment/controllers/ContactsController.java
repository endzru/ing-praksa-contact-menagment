package com.example.contactmenagment.controllers;


import com.example.contactmenagment.entity.Contacts;
import com.example.contactmenagment.services.implementation.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
public class ContactsController {
    private final ContactsService contactsServce;

    @GetMapping
    public ResponseEntity<List<Contacts>> getAllContacts(){
        return ResponseEntity.ok().body(contactsServce.getAllContacts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContactById(@PathVariable Long id){
        return ResponseEntity.ok().body(contactsServce.getContactById(id));
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<List<Contacts>> getContanctsByUserId(@PathVariable Long id){
        return ResponseEntity.ok().body(contactsServce.getAllByUserId(id));
    }
    @PostMapping
    public ResponseEntity<Contacts> saveContact(@RequestBody Contacts c){
        return ResponseEntity.ok().body(contactsServce.saveContact(c));
    }
    @PutMapping
    public ResponseEntity<Contacts> updateContact(@RequestBody Contacts c){
        return ResponseEntity.ok().body(contactsServce.updateContact(c));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Contacts> deleteContactById(@PathVariable Long id){
        return ResponseEntity.ok().body(contactsServce.deleteContactsByid(id));
    }
}
