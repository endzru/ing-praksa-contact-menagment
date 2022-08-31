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
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> getContactsByUid(UUID uid);
    void deleteByUidAndUser_Uid(UUID contactUid, UUID userUid);
    List<Contact> findAllByUser_Uid(UUID uid);
    Page<Contact> findAllByUser_Uid(UUID uid, Pageable pageable);

    Optional<Contact> findByUidAndUser_Uid(UUID contactUid, UUID userUid);


    @Query(value = "SELECT * FROM contacts WHERE contact_first_name LIKE CONCAT('%',:field, '%') OR " +
            "contact_last_name LIKE CONCAT('%',:field, '%')" +
            "OR contact_email LIKE CONCAT('%',:field, '%') " +
            "OR contact_phonenumber LIKE CONCAT('%',:field, '%') ", nativeQuery = true)
    Page<Contact> searchContact(String field, Pageable pageable);
    @Query(value = "SELECT * FROM contacts JOIN users ON contacts.user_id = users.id " +
            "WHERE users.uid = :userUid AND (contacts.contact_first_name LIKE CONCAT('%', :field, '%') " +
            "OR contacts.contact_last_name LIKE CONCAT('%', :field, '%')" +
            "OR contacts.contact_email LIKE CONCAT('%', :field, '%')" +
            "OR contacts.contact_phonenumber LIKE CONCAT('%', :field, '%'))", nativeQuery = true)
    Page<Contact> findByFieldPassedAndUser(String field, UUID userUid, Pageable pageable);

}