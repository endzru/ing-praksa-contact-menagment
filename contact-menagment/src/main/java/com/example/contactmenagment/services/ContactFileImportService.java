package com.example.contactmenagment.services;

import com.example.contactmenagment.controllers.dto.contactDTO.ContactRequestDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactFileImportService {

    private final ContactService contactService;
    public ResponseEntity importContactsFromFile(MultipartFile file) {
        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
                        contactService.saveContact(c);
                        contactsImported++;
                    }
                }
                if(!errors.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Some records were incomplete. " +
                            "Contacts imported: " + contactsImported + ".\n" + errors);
                } else if(contacts.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No contacts provided in the file.");
                } else {
                    return ResponseEntity.ok().build(); //body("All contacts were imported.")
                }
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File wasn't read. Either the format was incorrect or the file was corrupt.");
            }
        }
    }
}
