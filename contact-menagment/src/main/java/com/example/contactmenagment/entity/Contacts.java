package com.example.contactmenagment.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="contact_type_id")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //@Column(name="contact_first_name")
    private String contactFirstName;

    //@Column(name="contact_last_name")
    private String contactLastName;

   // @Column(name="contact_email")
    private String contactEmail;

   // @Column(name="contact_phonenumber")
    private String contactPhonenumber;

   // @Column(name="time_created")
    private LocalDateTime timeCreated;

   // @Column(name="time_updated")
    private LocalDateTime timeUpdated;

}
