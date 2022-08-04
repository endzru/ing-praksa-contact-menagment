package com.example.contactmenagment.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="contact_type_id")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String contactFirstName;

    private String contactLastName;

    private String contactEmail;

    private String contactPhonenumber;

    @Column(name="uid")
    private UUID uid;

    @CreationTimestamp
    @Column(name="time_created", updatable = false)
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;

}
