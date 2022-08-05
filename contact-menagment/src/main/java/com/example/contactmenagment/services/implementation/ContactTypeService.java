package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;

import com.example.contactmenagment.services.mappers.ContactTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeService  {

    private final ContactTypeRepository contactTypeRepository;

    private final ContactTypeMapper contactTypeMapper;
    @Transactional
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteContactTypeByUid(uid);
    }

    public List<ContactTypeResponseDTO> getAll() {
        return contactTypeMapper.mapFromContactTypeEntityToContactTypeDTO(contactTypeRepository.findAll());
    }

    public ContactType getContactByUid(UUID uid) {
        return contactTypeRepository.getContactTypeByUid(uid).orElseThrow(() -> new EntityNotFoundException("Contact type not found!"));
    }
    public ContactTypeResponseDTO getByUid(UUID uid){
        return contactTypeMapper.mapFromContactTypeEntityToContactTypeDTO(contactTypeRepository.getContactTypeByUid(uid).
                orElseThrow(() -> new EntityNotFoundException("Contact type not found!")));
    }
    public ContactType save(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

}
