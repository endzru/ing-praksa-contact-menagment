package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void deleteByUid(UUID uid) {
        roleRepository.deleteRoleByUid(uid);
    }
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByUid(UUID uid) {
        return roleRepository.getRoleByUid(uid).orElseThrow(() -> new EntityNotFoundException("No Role found!"));
    }

    public Role save(Role o) {
        return roleRepository.save( o);
    }



}