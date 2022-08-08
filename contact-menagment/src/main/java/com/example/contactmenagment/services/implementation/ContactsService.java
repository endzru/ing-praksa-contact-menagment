package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;

import com.example.contactmenagment.services.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactsService {

    private final ContactsRepository contactsRepository;
    private final ContactTypeService contactTypeService;
    private final UserService userService;
    private final ContactMapper contactMapper;

    @Transactional
    public void deleteByUid(UUID uid) {
        contactsRepository.deleteContactsByUid(uid);
    }

    public List<ContactResponseDTO> getAll() {
        return contactMapper.mapFromEntityToDTO(contactsRepository.findAll());
    }

    public Contact getContactByUid(UUID uid) {
        return contactsRepository.getContactsByUid(uid).orElseThrow(() -> new EntityNotFoundException("Contact not found!"));
    }

    public ContactResponseDTO getDTOByUid(UUID uid){
        return contactMapper.mapFromContactEntityToDTO(contactsRepository.getContactsByUid(uid).orElseThrow(()-> new EntityNotFoundException("Contact Not found")));
    }
    public Contact save(Contact contacts) {
        return contactsRepository.save(contacts);
    }

    public void saveContact(UUID uid, ContactRequestDTO contactDTO) {
        User s = userService.getUserByUid(uid);
        ContactType ct = contactTypeService.getContactTypeByUid(contactDTO.getContactTypeUID());
        Contact c = contactMapper.mapFromDTOToEntity(contactDTO);
        c.setUser(s);
        c.setContactType(ct);
        contactsRepository.save(c);
    }
    public void updateContact(UUID uid, ContactRequestDTO contactRequestDTO){
        Contact contact = contactMapper.mapFromDTOToEntityUpdate(uid, contactRequestDTO);
        contactsRepository.save(contact);
    }

}
