package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;

import com.example.contactmenagment.services.mappers.ContactTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<ContactTypeResponseDTO> getAllContactTypesResponseDTOPages(Pageable pageable){
        return contactTypeMapper.mapFromEntityList(contactTypeRepository.findAll(pageable));
    }

    public ContactType getContactTypeByUid(UUID uid) {
        return contactTypeRepository.getContactTypeByUid(uid).orElseThrow(() -> new EntityNotFoundException("Contact type not found!"));
    }
    public ContactTypeResponseDTO getByUid(UUID uid){
        return contactTypeMapper.mapFromContactTypeEntityToContactTypeDTO(contactTypeRepository.getContactTypeByUid(uid).
                orElseThrow(() -> new EntityNotFoundException("Contact type not found!")));
    }
    public void save(ContactTypeRequestDTO contactTypeRequestDTO) {
       contactTypeRepository.save(contactTypeMapper.mapFromEntityDTOToEntity(contactTypeRequestDTO));
    }
    public void updateContact(UUID uid, ContactTypeRequestDTO contactTypeRequestDTO){
        ContactType contactType = contactTypeMapper.mapFromEntityDTOToEntityUpdate(uid, contactTypeRequestDTO);
        contactTypeRepository.save(contactType);

    }
}
