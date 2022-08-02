package com.example.contactmenagment.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="contact_type")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="contact_type_name")
    private String contactTypeName;

}
