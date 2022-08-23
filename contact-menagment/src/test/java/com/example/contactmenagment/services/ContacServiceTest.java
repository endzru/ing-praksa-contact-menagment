package com.example.contactmenagment.services;

import com.example.contactmenagment.services.implementation.ContactFileImportService;
import com.example.contactmenagment.services.implementation.ContactsService;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
@RequiredArgsConstructor
public class ContacServiceTest {

    @Mock
    ContactsService contactsService;
    @InjectMocks
    ContactFileImportService contactFileImportService;

    @Test
    void testContactsImport() throws CsvRequiredFieldEmptyException, IOException {
        String s = "firstName,lastName,email,phonenumber,contactTypeUID\n" +
                "andrija,Stojanovic,astojanovic321@gmail.com,12312312,d7bc200c-67fd-4f41-bc64-b29fd6cc503b\n" +
                "Jovan,Spasic,jovan@gmail.com,123412,38907674-60c0-49b6-93a5-016bd2d9f73a\n" +
                "Stefan,Gogic,gogic@gmail.com,5312334,41938d59-6bb4-410d-832f-e76fe249c93c";
        MultipartFile sampleFile = new MockMultipartFile(
                "file",
                "file.csv",
                "text/csv",
                s.getBytes()
        );

        Mockito.doNothing().when(contactsService).saveContact(Mockito.any());
        Assertions.assertEquals(ResponseEntity.ok().build(), contactFileImportService.importContactsFromFile(sampleFile));

    }
    @Test
    void testContactsImport1() throws CsvRequiredFieldEmptyException, IOException {
        String s = "firstName,lastName,email,phonenumber,contactTypeUID\n" +
                "andrija,Stojanovic,astojanovic321@gmail.com,,d7bc200c-67fd-4f41-bc64-b29fd6cc503b\n" +
                "Jovan,Spasic,jovan@gmail.com,123412,\n" +
                "Stefan,Gogic,gogic@gmail.com,5312334,41938d59-6bb4-410d-832f-e76fe249c93c";
        MultipartFile sampleFile = new MockMultipartFile(
                "file",
                "file.csv",
                "text/csv",
                s.getBytes()
        );

        Mockito.doNothing().when(contactsService).saveContact(Mockito.any());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build().getStatusCode(), contactFileImportService.importContactsFromFile(sampleFile).getStatusCode());

    }
    @Test
    void testContactsImpor2() throws CsvRequiredFieldEmptyException, IOException {
        String s = "";
        MultipartFile sampleFile = new MockMultipartFile(
                "file",
                "file.csv",
                "text/csv",
                s.getBytes()
        );

        Mockito.doNothing().when(contactsService).saveContact(Mockito.any());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build().getStatusCode(), contactFileImportService.importContactsFromFile(sampleFile).getStatusCode());

    }
    @Test
    void testContactsImport3() throws CsvRequiredFieldEmptyException, IOException {
        String s = "asdffdsa";
        MultipartFile sampleFile = new MockMultipartFile(
                "file",
                "file.csv",
                "text/csv",
                s.getBytes()
        );

        Mockito.doNothing().when(contactsService).saveContact(Mockito.any());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode(), contactFileImportService.importContactsFromFile(sampleFile).getStatusCode());

    }
}
