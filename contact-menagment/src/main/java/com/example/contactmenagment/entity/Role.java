package com.example.contactmenagment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String roleName;

    @Column(name="uid")
    private UUID uid;


}
