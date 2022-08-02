package com.example.contactmenagment.services.serviceInterface;

import com.example.contactmenagment.entity.Contact;

import java.util.List;

public interface ContactServiceInterface {

    //GET
    List<Contact> findAllContacts();
    Contact getContactById(Integer id);

    //POST
    Contact saveContact(Contact r);

    //PUT
    Contact updateContact(Contact r);

    //DELETE
    void deleteContactById(Integer id);

}
