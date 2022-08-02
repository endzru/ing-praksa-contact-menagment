package com.example.contactmenagment.services.serviceImplementation;

import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.repository.ContactRepository;
import com.example.contactmenagment.services.serviceInterface.ContactServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class ContactService implements ContactServiceInterface {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Contact found!"));
    }

    @Override
    public Contact saveContact(Contact r) {
        return contactRepository.save(r);
    }

    @Override
    public Contact updateContact(Contact r) {
        return contactRepository.save(r);
    }

    @Override
    public void deleteContactById(Integer id) {
        contactRepository.deleteById(id);
    }
}
