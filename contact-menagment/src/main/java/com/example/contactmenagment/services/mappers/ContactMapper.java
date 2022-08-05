package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.entity.Contacts;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ContactMapper {
    public List<ContactResponseDTO> mapFromEntityToDTO(List<Contacts> contactsList) {
        List<ContactResponseDTO> dtoList = new ArrayList<>();
        for(Contacts t : contactsList ){
            ContactResponseDTO contactDto = new ContactResponseDTO();
            contactDto.setFirstName(t.getContactFirstName());
            contactDto.setLastName(t.getContactLastName());
            contactDto.setPhonenumber(t.getContactPhonenumber());
            contactDto.setUid(t.getUid());
            contactDto.setContactType(t.getContactType().getContactTypeName());
            contactDto.setTimeCreated(t.getTimeCreated());
            contactDto.setTimeUpdated(t.getTimeUpdated());
            dtoList.add(contactDto);
        }
        return dtoList;
    }
    public ContactResponseDTO mapFromContactEntityToDTO(Contacts contact){
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setUid(contact.getUid());
        contactResponseDTO.setFirstName(contact.getContactFirstName());
        contactResponseDTO.setLastName(contact.getContactLastName());
        contactResponseDTO.setEmail(contact.getContactEmail());
        contactResponseDTO.setPhonenumber(contact.getContactPhonenumber());
        contactResponseDTO.setContactType(contact.getContactType().getContactTypeName());
        contactResponseDTO.setTimeCreated(contact.getTimeCreated());
        contactResponseDTO.setTimeUpdated(contact.getTimeUpdated());

        return contactResponseDTO;
    }
    public Contacts mapFromDTOToEntity(ContactRequestDTO contactRequestDTO) {
        Contacts cont = new Contacts();
        cont.setContactFirstName(contactRequestDTO.getFirstName());
        cont.setContactLastName(contactRequestDTO.getLastName());
        cont.setContactEmail(contactRequestDTO.getEmail());
        cont.setContactPhonenumber(contactRequestDTO.getPhonenumber());
        cont.setUid(UUID.randomUUID());
        return cont;
    }
}
