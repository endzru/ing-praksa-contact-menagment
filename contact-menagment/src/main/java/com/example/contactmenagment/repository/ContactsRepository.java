package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> getContactsByUid(UUID uid);
    void deleteContactsByUid(UUID uid);

    List<Contact> findAllByUser_Uid(UUID uid);
}