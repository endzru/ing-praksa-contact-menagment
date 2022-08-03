package com.example.contactmenagment.entity;


import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name="contact_type")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name="contact_type_name")
    private String contactTypeName;

    //@Column(name="time_created")
    private LocalDateTime timeCreated;

    //@Column(name="time_updated")
    private LocalDateTime timeUpdated;
}
