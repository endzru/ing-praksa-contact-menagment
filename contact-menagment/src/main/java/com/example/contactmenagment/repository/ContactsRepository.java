package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    Contacts getContactsByUid(UUID uid);
    void deleteContactsByUid(UUID uid);

    List<Contacts> findAllByUser_Uid(UUID uid);
}