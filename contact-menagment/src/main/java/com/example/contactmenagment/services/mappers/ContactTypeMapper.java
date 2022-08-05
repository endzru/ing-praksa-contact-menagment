package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import com.example.contactmenagment.entity.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {

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

}
