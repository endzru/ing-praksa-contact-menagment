package com.example.contactmenagment.entity;


import lombok.Data;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_role")
    private Role idRole;

    @Column(name="time_created")
    private LocalDateTime timeCreated;

    @Column(name="time_updated")
    private LocalDateTime timeUpdated;

}
