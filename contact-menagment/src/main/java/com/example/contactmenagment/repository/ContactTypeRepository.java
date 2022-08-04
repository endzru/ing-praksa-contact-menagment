package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.ContactType;
import com.example.contactmenagment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
    ContactType getContactTypeByUid(UUID uid);

    void deleteContactTypeByUid(UUID uid);
}