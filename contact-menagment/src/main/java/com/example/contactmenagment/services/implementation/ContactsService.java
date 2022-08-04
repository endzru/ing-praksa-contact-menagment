package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Contacts;
import com.example.contactmenagment.repository.ContactsRepository;
//import com.example.contactmenagment.services.interfaces.ContactsServiceInterface;
import com.example.contactmenagment.services.interfaces.ServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactsService implements ServicesInterface<Contacts> {

    private final ContactsRepository contactsRepository;

    @Override
    public void deleteByUid(UUID uid) {
        contactsRepository.deleteContactsByUid(uid);
    }

    @Override
    public List<Contacts> getAll() {
        return contactsRepository.findAll();
    }

    @Override
    public Contacts getByUid(UUID uid) {
        return contactsRepository.getContactsByUid(uid);
    }

    @Override
    public Contacts save(Contacts contacts) {
        return contactsRepository.save(contacts);
    }

}
