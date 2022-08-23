package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {


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
    public ContactType mapFromEntityDTOToEntityUpdate( ContactTypeRequestDTO contactTypeRequestDTO, ContactType contactType){
        contactType.setContactTypeName(contactTypeRequestDTO.getContactTypeName());
        return contactType;
    }

}
