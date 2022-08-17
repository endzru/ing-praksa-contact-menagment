package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    @Transactional
    public void deleteByUid(UUID uid) {
        roleRepository.deleteRoleByUid(uid);
    }

    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Role getByUid(UUID roleUid) {
        return roleRepository.getRoleByUid(roleUid).orElseThrow(() -> new EntityNotFoundException("No Role found!"));
    }
    @Transactional
    public Role save(Role o) {
        o.setUid(UUID.randomUUID());
        return roleRepository.save( o);
    }
    @Transactional
    public Role updateRole(UUID roleUid, Role role){
        Role r = getByUid(roleUid);
        r.setRoleName(role.getRoleName());

        return roleRepository.save(r);
    }
}