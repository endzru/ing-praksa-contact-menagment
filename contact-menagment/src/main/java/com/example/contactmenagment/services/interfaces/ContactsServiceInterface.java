package com.example.contactmenagment.services.interfaces;

import com.example.contactmenagment.entity.Contacts;

import java.util.List;

public interface ContactsServiceInterface {

    List<Contacts> getAllContacts();

    Contacts getContactById(Long id);

    Contacts saveContact(Contacts c);

    Contacts updateContact(Contacts c);

    Contacts deleteContactsByid(Long id);

    List<Contacts> getAllByUserId(Long id);
}
