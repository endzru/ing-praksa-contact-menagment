package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactMapper {


    public List<ContactResponseDTO> mapFromEntityToDTO(List<Contact> contactsList) {
        List<ContactResponseDTO> dtoList = new ArrayList<>();
        for (Contact t : contactsList) {
            ContactResponseDTO contactDto = mapFromContactEntityToDTO(t);
            dtoList.add(contactDto);
        }
        return dtoList;
    }

    public ContactResponseDTO mapFromContactEntityToDTO(Contact contact) {
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

    public Contact mapFromDTOToEntity(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        mapContactDtoToContact(contactRequestDTO, contact);
        contact.setUid(UUID.randomUUID());
        return contact;
    }

    public Contact mapFromDTOToEntity(ContactRequestDTO contactRequestDTO, Contact contact) {
        mapContactDtoToContact(contactRequestDTO, contact);
        return contact;
    }

    public void mapContactDtoToContact(ContactRequestDTO contactRequestDTO, Contact contact) {
        contact.setContactFirstName(contactRequestDTO.getFirstName());
        contact.setContactLastName(contactRequestDTO.getLastName());
        contact.setContactEmail(contactRequestDTO.getEmail());
        contact.setContactPhonenumber(contactRequestDTO.getPhonenumber());
    }

    public Page<ContactResponseDTO> mapFromEntityList(Page<Contact> contactPage) {
        return contactPage.map(this::mapFromContactEntityToDTO);
    }
}
