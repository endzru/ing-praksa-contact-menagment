package com.example.contactmenagment.services.serviceImplementation;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.repository.RoleRepository;
import com.example.contactmenagment.services.serviceInterface.RoleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Role found!"));
    }

    @Override
    public Role saveRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public Role updateRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }
}
