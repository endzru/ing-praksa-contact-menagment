package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {
    private final ContactTypeRepository contactTypeRepository;

    public List<ContactTypeResponseDTO> mapFromContactTypeEntityToContactTypeDTO(List<ContactType> contactTypeList){
        List<ContactTypeResponseDTO> contactResponseDTOList = new ArrayList<>();
        for (ContactType contactType : contactTypeList ) {
            ContactTypeResponseDTO contactTypeResponseDTO = new ContactTypeResponseDTO();
            contactTypeResponseDTO.setUid(contactType.getUid());
            contactTypeResponseDTO.setContactTypeName(contactType.getContactTypeName());
            contactTypeResponseDTO.setTimeCreated(contactType.getTimeCreated());
            contactTypeResponseDTO.setTimeUpdated(contactType.getTimeUpdated());
            contactResponseDTOList.add(contactTypeResponseDTO);
        }
        return contactResponseDTOList;
    }

    public ContactTypeResponseDTO mapFromContactTypeEntityToContactTypeDTO(ContactType contactType){
        ContactTypeResponseDTO contactTypeResponseDTO = new ContactTypeResponseDTO();
        contactTypeResponseDTO.setUid(contactType.getUid());
        contactTypeResponseDTO.setContactTypeName(contactType.getContactTypeName());
        contactTypeResponseDTO.setTimeCreated(contactType.getTimeCreated());
        contactTypeResponseDTO.setTimeUpdated(contactType.getTimeUpdated());

        return contactTypeResponseDTO;
    }
    public ContactType mapFromEntityDTOToEntity(ContactTypeRequestDTO contactTypeRequestDTO ){
        ContactType contactType = new ContactType();
        contactType.setContactTypeName(contactTypeRequestDTO.getContactTypeName());
        contactType.setUid(UUID.randomUUID());

        return contactType;
    }
    public Page<ContactTypeResponseDTO> mapFromEntityList(Page<ContactType> contactTypePage){
        return contactTypePage.map(this::mapFromContactTypeEntityToContactTypeDTO);
    }
    public ContactType mapFromEntityDTOToEntityUpdate(UUID uid, ContactTypeRequestDTO contactTypeRequestDTO){
        ContactType contactType = contactTypeRepository.getContactTypeByUid(uid).orElseThrow(() -> new EntityNotFoundException("No ContactType Found!"));
        contactType.setContactTypeName(contactTypeRequestDTO.getContactTypeName());

        return contactType;
    }

}
