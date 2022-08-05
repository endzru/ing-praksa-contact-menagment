package com.example.contactmenagment.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="contact_type")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactTypeName;

    @CreationTimestamp
    @Column(name="time_created", updatable = false)
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    @Column(name="uid")
    private UUID uid;
}
