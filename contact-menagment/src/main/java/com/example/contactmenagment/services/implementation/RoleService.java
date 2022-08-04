package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.repository.RoleRepository;
import com.example.contactmenagment.services.interfaces.RoleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Role for id: " + id + " found!"));
    }

    @Override
    public Role saveRole(Role c) {
        return roleRepository.save(c);
    }

    @Override
    public Role updateRole(Role c) {
        return roleRepository.save(c);
    }

    @Override
    public Role deleteRoleById(Long id) {
        Role temp = roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Role for id: " + id + " found!"));
        roleRepository.deleteById(id);
        return temp;
    }
}
