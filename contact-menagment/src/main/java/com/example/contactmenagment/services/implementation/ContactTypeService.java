package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;
//import com.example.contactmenagment.services.interfaces.ContactTypeServiceInterface;
import com.example.contactmenagment.services.interfaces.ServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeService implements ServicesInterface<ContactType> {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteContactTypeByUid(uid);
    }

    @Override
    public List<ContactType> getAll() {
        return contactTypeRepository.findAll();
    }

    @Override
    public ContactType getByUid(UUID uid) {
        return contactTypeRepository.getContactTypeByUid(uid);
    }

    @Override
    public ContactType save(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

}
