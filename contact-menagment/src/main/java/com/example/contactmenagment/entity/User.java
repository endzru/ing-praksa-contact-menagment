package com.example.contactmenagment.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    @Column(name = "uid", unique = true)
    private UUID uid;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Contacts> contacts;

    @Column(name="time_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;
}
