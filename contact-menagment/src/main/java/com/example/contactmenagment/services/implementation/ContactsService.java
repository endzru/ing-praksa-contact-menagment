package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Contacts;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.services.interfaces.ContactsServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactsService implements ContactsServiceInterface {

    private final ContactsRepository contactsRepository;

    @Override
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public Contacts getContactById(Long id) {
        return contactsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Contact for id: " + id + " found!"));
    }

    @Override
    public Contacts saveContact(Contacts c) {
        return contactsRepository.save(c);
    }

    @Override
    public Contacts updateContact(Contacts c) {
        return contactsRepository.save(c);
    }

    @Override
    public Contacts deleteContactsByid(Long id) {
        Contacts temp = getContactById(id);
        contactsRepository.deleteById(id);
        return temp;
    }

    @Override
    public List<Contacts> getAllByUserId(Long id){
        return contactsRepository.findAllByUserId(id);
    }
}
