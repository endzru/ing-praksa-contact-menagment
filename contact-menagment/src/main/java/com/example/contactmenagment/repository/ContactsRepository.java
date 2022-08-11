package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> getContactsByUid(UUID uid);
    void deleteContactsByUid(UUID uid);
    List<Contact> findAllByUser_Uid(UUID uid);
    Page<Contact> findAllByUser_Uid(UUID uid, Pageable pageable);

    @Query(value = "SELECT * FROM contacts WHERE contact_first_name LIKE CONCAT('%',:field, '%') OR " +
            "contact_last_name LIKE CONCAT('%',:field, '%')" +
            "OR contact_email LIKE CONCAT('%',:field, '%') " +
            "OR contact_phonenumber LIKE CONCAT('%',:field, '%') ", nativeQuery = true)
    List<Contact> searchContactByContactFirstName(String field);

}