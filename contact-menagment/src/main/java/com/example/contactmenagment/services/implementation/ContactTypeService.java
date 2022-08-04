package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;
import com.example.contactmenagment.services.interfaces.ContactTypeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactTypeService implements ContactTypeServiceInterface {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactType> getAllContactTypes() {
        return contactTypeRepository.findAll();
    }

    @Override
    public ContactType getContactTypeById(Long id) {
        return contactTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No ContactType for id: " + id + " found!"));
    }

    @Override
    public ContactType saveContactType(ContactType cc) {
        return contactTypeRepository.save(cc);
    }

    @Override
    public ContactType updateContactType(ContactType cc) {
        return contactTypeRepository.save(cc);
    }

    @Override
    public ContactType deleteContactTypeById(Long id) {
        ContactType c = getContactTypeById(id);
        contactTypeRepository.deleteById(id);

        return c;
    }
}
