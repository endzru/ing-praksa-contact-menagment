package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.RoleRepository;
//import com.example.contactmenagment.services.interfaces.RoleServiceInterface;
import com.example.contactmenagment.services.interfaces.ServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService implements ServicesInterface<Role> {

    private final RoleRepository roleRepository;

    @Override
    public void deleteByUid(UUID uid) {
        roleRepository.deleteRoleByUid(uid);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getByUid(UUID uid) {
        return roleRepository.getRoleByUid(uid);
    }

    @Override
    public Role save(Role o) {
        return roleRepository.save( o);
    }



}