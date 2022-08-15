package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsRepository contactRepository;
    private final ContactTypeService contactTypeService;
    private final UserService userService;
    private final ContactMapper contactMapper;

    private final UserRepository userRepository;

    @Transactional
    public void deleteByUid(UUID contactUid) {
        UUID pomUid = getLoggedInUser().getUid();
        Contact contact = contactRepository.getContactsByUid(contactUid).orElseThrow(() -> new EntityNotFoundException("Contact not found"));
        if(contact.getUser().getUid() == pomUid ) {
            contactRepository.deleteByUidAndUser_Uid(contactUid, pomUid);
        } else {
            throw new AccessDeniedException("Not Allowed");
        }
    }

    public Page<ContactResponseDTO> getAll(Pageable pageable) {
        User user = getLoggedInUser();
        return contactMapper.mapFromEntityList(contactRepository.findAllByUser_Uid(user.getUid(), pageable));
    }

    public Contact getContactByUid(UUID contactUid) {
        return contactRepository.getContactsByUid(contactUid).orElseThrow(() -> new EntityNotFoundException("Contact not found!"));
    }

    public ContactResponseDTO getDTOByUid(UUID contactUid, UUID userUid)  {
        return contactMapper.mapFromContactEntityToDTO(contactRepository.findByUidAndUser_Uid(contactUid,userUid).orElseThrow(()-> new EntityNotFoundException("Contact not found!")));
    }


    public void saveContact(ContactRequestDTO contactDTO) {
        User s = getLoggedInUser();
        ContactType ct = contactTypeService.getContactTypeByUid(contactDTO.getContactTypeUID());
        Contact c = contactMapper.mapFromDTOToEntity(contactDTO);
        c.setUser(s);
        c.setContactType(ct);
        contactRepository.save(c);
    }

    public List<ContactResponseDTO> searchContacts(String field) {
        return contactMapper.mapFromEntityToDTO(contactRepository.searchContactByContactFirstName(field));
    }
    public void updateContact(UUID contactUid, ContactRequestDTO contactRequestDTO) {
        User u = getLoggedInUser();

        Contact contact = contactMapper.mapFromDTOToEntityUpdate(contactUid, contactRequestDTO);
        if(contact.getUser().getUid() == u.getUid()){
            contactRepository.save(contact);
        }else{

            throw new AccessDeniedException("Not allowed!");
        }
    }
    public User getLoggedInUser() {
        String userEmail = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userEmail = authentication.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login required.");
        }
        final String email = userEmail;

        return user;
    }

}
