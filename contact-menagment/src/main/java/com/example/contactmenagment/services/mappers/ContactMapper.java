package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.repository.ContactTypeRepository;
import com.example.contactmenagment.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final ContactTypeRepository contactTypeRepository;
    private final ContactsRepository contactsRepository;
    public List<ContactResponseDTO> mapFromEntityToDTO(List<Contact> contactsList) {
        List<ContactResponseDTO> dtoList = new ArrayList<>();
        for(Contact t : contactsList ){
            ContactResponseDTO contactDto = mapFromContactEntityToDTO(t);
            dtoList.add(contactDto);
        }
        return dtoList;
    }
    public ContactResponseDTO mapFromContactEntityToDTO(Contact contact){
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
    public Contact mapFromDTOToEntity(ContactRequestDTO contactRequestDTO, Contact contact) {
        if(contact == null ){
            contact = new Contact();
            contact.setContactFirstName(contactRequestDTO.getFirstName());
            contact.setContactLastName(contactRequestDTO.getLastName());
            contact.setContactEmail(contactRequestDTO.getEmail());
            contact.setContactPhonenumber(contactRequestDTO.getPhonenumber());
            contact.setUid(UUID.randomUUID());
        }else{
            contact.setContactFirstName(contactRequestDTO.getFirstName());
            contact.setContactLastName(contactRequestDTO.getLastName());
            contact.setContactEmail(contactRequestDTO.getEmail());
            contact.setContactPhonenumber(contactRequestDTO.getPhonenumber());
        }
        return contact;
    }
    public Page<ContactResponseDTO> mapFromEntityList(Page<Contact> contactPage){
        return contactPage.map(this::mapFromContactEntityToDTO);
    }
}
