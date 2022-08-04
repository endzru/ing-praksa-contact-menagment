package com.example.contactmenagment.services.interfaces;


import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.Contacts;

import java.util.List;

public interface ContactTypeServiceInterface {

    List<ContactType> getAllContactTypes();

    ContactType getContactTypeById(Long id);

    ContactType saveContactType(ContactType user);

    ContactType updateContactType(ContactType user);

    ContactType deleteContactTypeById(Long id);

}
