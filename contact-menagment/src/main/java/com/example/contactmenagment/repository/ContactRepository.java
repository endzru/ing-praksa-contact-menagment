package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.Contact;
import com.example.contactmenagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

//    List<Contact> getAllByUser(User u);
}