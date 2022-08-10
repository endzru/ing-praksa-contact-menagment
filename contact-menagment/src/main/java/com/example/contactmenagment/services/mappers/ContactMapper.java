package com.example.contactmenagment.services.mappers;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.repository.ContactTypeRepository;
import com.example.contactmenagment.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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
            ContactResponseDTO contactDto = new ContactResponseDTO();
            contactDto.setFirstName(t.getContactFirstName());
            contactDto.setLastName(t.getContactLastName());
            contactDto.setEmail(t.getContactEmail());
            contactDto.setPhonenumber(t.getContactPhonenumber());
            contactDto.setUid(t.getUid());
            contactDto.setContactType(t.getContactType().getContactTypeName());
            contactDto.setTimeCreated(t.getTimeCreated());
            contactDto.setTimeUpdated(t.getTimeUpdated());
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
    public Contact mapFromDTOToEntity(ContactRequestDTO contactRequestDTO) {
        Contact cont = new Contact();
        cont.setContactFirstName(contactRequestDTO.getFirstName());
        cont.setContactLastName(contactRequestDTO.getLastName());
        cont.setContactEmail(contactRequestDTO.getEmail());
        cont.setContactPhonenumber(contactRequestDTO.getPhonenumber());
        cont.setUid(UUID.randomUUID());
        return cont;
    }
    public Contact mapFromDTOToEntityUpdate(UUID uid, ContactRequestDTO contactRequestDTO){
        Contact contact = contactsRepository.getContactsByUid(uid).orElseThrow(() -> new EntityNotFoundException("No Contact found!"));
        contact.setContactFirstName(contactRequestDTO.getFirstName());
        contact.setContactLastName(contactRequestDTO.getLastName());
        contact.setContactEmail(contactRequestDTO.getEmail());
        contact.setContactPhonenumber(contactRequestDTO.getPhonenumber());
        contact.setContactType(contactTypeRepository.getContactTypeByUid(contactRequestDTO.getContactTypeUID()).orElseThrow(() -> new EntityNotFoundException("Contact Type not found!")));

        return contact;
    }
    public Page<ContactResponseDTO> mapFromEntityList(Page<Contact> contactPage){
        return contactPage.map(this::mapFromContactEntityToDTO);
    }
}
