package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactDTO.ContactRequestDTO;
import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.services.mappers.ContactMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ContactsService {

    Logger logger = LoggerFactory.getLogger(ContactsService.class);
    private final ContactsRepository contactRepository;
    private final ContactTypeService contactTypeService;
    private final ContactMapper contactMapper;
    private final Validator validator;


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
        return contactMapper.mapFromEntityList(contactRepository.searchContactByContactFirstName(field, pageable));
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

    public ResponseEntity importContactsFromFile(MultipartFile file) {
        int contactsImported = 0;
        if(file.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<String> errors = new ArrayList<>();
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                CsvToBean<ContactRequestDTO> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(ContactRequestDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .build();
                List<ContactRequestDTO> contacts = csvToBean.parse();
                int i = 1;
                for (ContactRequestDTO c : contacts) {
                    if(validator.validate(c).size() != 0) {
                        String s = "Contact " + i + " :";
                        i++;
                        validator.validate(c).forEach(cv -> { errors.add(s + cv.getMessage()); });
                    } else {
                        this.saveContact(c);
                        contactsImported++;
                    }
                }
                if(!errors.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Some records were incomplete. " +
                            "Contacts imported: " + contactsImported + ".\n" + errors);
                } else if(contacts.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No contacts provided in the file.");
                } else {
                    return ResponseEntity.ok().body("All contacts were imported.");
                }
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File wasn't read. Either the format was incorrect or the file was corrupt.");
            }
        }
    }

}

