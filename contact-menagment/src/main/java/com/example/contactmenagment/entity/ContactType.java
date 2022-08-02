package com.example.contactmenagment.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact_type", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "contact_type_name")
    @Type(type="org.hibernate.type.TextType")
    private String contactTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

}