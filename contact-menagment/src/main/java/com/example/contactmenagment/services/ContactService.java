package com.example.contactmenagment.services;

import com.example.contactmenagment.controllers.dto.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.dto.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactRepository;
import com.example.contactmenagment.services.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ContactService {

    Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final ContactRepository contactRepository;
    private final ContactTypeService contactTypeService;
    private final ContactMapper contactMapper;


    @Transactional
    public void deleteByUid(UUID contactUid) {
        contactRepository.deleteByUidAndUser_Uid(contactUid, getContactAndValidate(contactUid).getUser().getUid());
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseDTO> getAll(Pageable pageable) {
        User user = getLoggedInUser();
        return contactMapper.mapFromEntityList(contactRepository.findAllByUser_Uid(user.getUid(), pageable));
    }

    @Transactional(readOnly = true)
    public Contact getContactByUid(UUID contactUid) {
        return contactRepository.getContactsByUid(contactUid).orElseThrow(() -> new EntityNotFoundException("Contact not found!"));
    }

    public ContactResponseDTO getDTOByUid(UUID contactUid, UUID userUid) {
        return contactMapper.mapFromContactEntityToDTO(contactRepository.findByUidAndUser_Uid(contactUid, userUid).orElseThrow(() -> new EntityNotFoundException("Contact not found!")));
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseDTO> searchContacts(String field, Pageable pageable) {
        return contactMapper.mapFromEntityList(contactRepository.searchContact(field, pageable));
    }

    @Transactional
    public void saveContact(ContactRequestDTO contactDTO) {
        User s = getLoggedInUser();
        ContactType ct = contactTypeService.getContactTypeByUid(contactDTO.getContactTypeUID());
        Contact c = contactMapper.mapFromDTOToEntity(contactDTO);
        c.setUser(s);
        c.setContactType(ct);
        contactRepository.save(c);
    }

    @Transactional
    public void updateContact(UUID contactUid, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactMapper.mapFromDTOToEntity(contactRequestDTO, getContactAndValidate(contactUid));
        ContactType contactType = contactTypeService.getContactTypeByUid(contactRequestDTO.getContactTypeUID());
        contact.setContactType(contactType);

        contactRepository.save(contact);
    }

    private Contact getContactAndValidate(UUID contactUid) {
        User u = getLoggedInUser();
        Contact contactHolder = getContactByUid(contactUid);

        if (contactHolder.getUser().getUid().equals(u.getUid())) {
            return contactHolder;
        } else {

            throw new AccessDeniedException("No access!");
        }
    }
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login required.");
        }
    }


    public Page<ContactResponseDTO> getAllByFieldAdmin(String field, Pageable pageable) {
        return contactMapper.mapFromEntityList(contactRepository.findByFieldPassedAndUser(field, getLoggedInUser().getUid(), pageable));
    }



}

