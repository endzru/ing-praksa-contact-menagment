package com.example.contactmenagment.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contact_type")
    private ContactType idContactType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User idUser;

    @Lob
    @Column(name = "contact_first_name")
    @Type(type="org.hibernate.type.TextType")
    private String contactFirstName;

    @Lob
    @Column(name = "contact_last_name")
    @Type(type="org.hibernate.type.TextType")
    private String contactLastName;

    @Lob
    @Column(name = "contact_email")
    @Type(type="org.hibernate.type.TextType")
    private String contactEmail;

    @Lob
    @Column(name = "contact_phonenumber")
    @Type(type="org.hibernate.type.TextType")
    private String contactPhonenumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContactType getIdContactType() {
        return idContactType;
    }

    public void setIdContactType(ContactType idContactType) {
        this.idContactType = idContactType;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhonenumber() {
        return contactPhonenumber;
    }

    public void setContactPhonenumber(String contactPhonenumber) {
        this.contactPhonenumber = contactPhonenumber;
    }

}