package com.example.contactmenagment.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @NotBlank
    private String password;

    @NotNull
    @Column(name = "uid", unique = true)
    private UUID uid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;


//    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch= FetchType.LAZY)
    private List<Contact> contacts;


    @Column(name="time_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime timeCreated;


    @UpdateTimestamp
    private LocalDateTime timeUpdated;
}
