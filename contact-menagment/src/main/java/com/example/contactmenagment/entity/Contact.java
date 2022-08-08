package com.example.contactmenagment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="contacts")
@RequiredArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_type_id")
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.LAZY)
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
