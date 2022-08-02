package com.example.contactmenagment.services.serviceInterface;

import com.example.contactmenagment.entity.ContactType;

import java.util.List;

public interface ContactTypeServiceInterface {

    //GET
    List<ContactType> findAllContactType();
    ContactType getContactTypeById(Integer id);

    //POST
    ContactType saveRole(ContactType r);

    //PUT
    ContactType updateRole(ContactType r);

    //DELETE
    void deleteContactTypeById(Integer id);


}
