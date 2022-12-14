package com.example.contactmenagment.entity;


import com.example.contactmenagment.controllers.dto.userDTO.UserRequestDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "email must be in a valid format.")
    private String email;

    @NotBlank
    @Size(max = 30, message = "firstName can contain up to 30 characters")
    private String firstName;

    @Size(max = 30, message = "lastName can contain up to 30 characters")
    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotNull
    @Column(name = "uid", unique = true)
    private UUID uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Contact> contacts;

    @Column(name = "time_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    @Column(name="phonenumber")
    private String phonenumber;

    @NotNull
    @Column(name="status")
    private String status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole().getRoleName()));
        //authorities.add(new SimpleGrantedAuthority(this.getStatus()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; //getStatus().equals("VERIFIED");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        UserRequestDTO user = (UserRequestDTO) o;
        return Objects.equals(email, user.getEmail())
                && Objects.equals(firstName, user.getFirstName())
                && Objects.equals(lastName, user.getLastName())
                && Objects.equals(password, user.getPassword())
                && Objects.equals(phonenumber, user.getPhonenumber());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", uid=" + uid +
                ", role=" + role +
                ", contacts=" + contacts +
                ", timeCreated=" + timeCreated +
                ", timeUpdated=" + timeUpdated +
                ", phonenumber='" + phonenumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
