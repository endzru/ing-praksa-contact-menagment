package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;
import com.example.contactmenagment.services.mappers.ContactTypeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeService  {

    Logger logger = LoggerFactory.getLogger(ContactsService.class);

    private final ContactTypeRepository contactTypeRepository;
    private final ContactTypeMapper contactTypeMapper;

    @Transactional(readOnly = true)
    public Page<ContactTypeResponseDTO> getAllContactTypesResponseDTOPages(Pageable pageable){
        return contactTypeMapper.mapFromEntityList(contactTypeRepository.findAll(pageable));
    }
    @Transactional(readOnly = true)
    public ContactType getContactTypeByUid(UUID contactTypeUid) {
        return contactTypeRepository.getContactTypeByUid(contactTypeUid).orElseThrow(() -> new EntityNotFoundException("Contact type not found!"));
    }
    @Transactional(readOnly = true)
    public ContactTypeResponseDTO getContactTypeDTOByUid(UUID contactTypeUid){
        return contactTypeMapper.mapFromContactTypeEntityToContactTypeDTO(getContactTypeByUid(contactTypeUid));
    }
    @Transactional
    public void save(ContactTypeRequestDTO contactTypeRequestDTO) {
       contactTypeRepository.save(contactTypeMapper.mapFromEntityDTOToEntity(contactTypeRequestDTO));
    }
    @Transactional
    public void updateContactType(UUID contactTypeUid, ContactTypeRequestDTO contactTypeRequestDTO){
        ContactType contactType = getContactTypeByUid(contactTypeUid);
        contactTypeMapper.mapFromEntityDTOToEntityUpdate(contactTypeRequestDTO, contactType);
        contactTypeRepository.save(contactType);
    }
    @Transactional
    public void deleteByUid(UUID contactTypeUid) {
        contactTypeRepository.deleteContactTypeByUid(contactTypeUid);
    }

}
