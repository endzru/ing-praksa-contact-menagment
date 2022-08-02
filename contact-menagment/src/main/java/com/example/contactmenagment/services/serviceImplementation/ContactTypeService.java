package com.example.contactmenagment.services.serviceImplementation;

import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;

import com.example.contactmenagment.services.serviceInterface.ContactTypeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ContactTypeService implements ContactTypeServiceInterface {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactType> findAllContactType() {
        return contactTypeRepository.findAll();
    }

    @Override
    public ContactType getContactTypeById(Integer id) {
        return contactTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No ContactType found!"));
    }

    @Override
    public ContactType saveRole(ContactType r) {
        return contactTypeRepository.save(r);
    }

    @Override
    public ContactType updateRole(ContactType r) {
        return contactTypeRepository.save(r);
    }

    @Override
    public void deleteContactTypeById(Integer id) {
        contactTypeRepository.deleteById(id);
    }
}
