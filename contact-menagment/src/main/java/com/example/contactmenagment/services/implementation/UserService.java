package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.mailService.EmailServiceImplementation;
import com.example.contactmenagment.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    private final EmailServiceImplementation emailServiceImplementation;

    @Transactional
    public void deleteByUid(UUID userUid) {
        userRepository.deleteUserByUid(userUid);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userMapper.mapFromEntityList(userRepository.findAll(pageable));
    }

    @Transactional(readOnly = true)
    public User getUserByUid(UUID userUid) {
        return userRepository.findUserByUid(userUid).orElseThrow(() -> new EntityNotFoundException("No User found!"));
    }

    @Transactional
    public ResponseEntity<UserResponseDTO> getUserDTOByUid(UUID userUid) {
        return ResponseEntity.ok().body(userMapper.mapFromUserToUserDTO(getUserByUid(userUid)));
    }

    @Transactional
    public void save(UserRequestDTO userRequestDTO) {
        User user = userMapper.mapFromUserDTOToUser(userRequestDTO);
        user.setRole(roleService.getByUid(userRequestDTO.getRoleid()));
        userRepository.save(user);

        emailServiceImplementation.sendSimpleMessage(userRequestDTO.getEmail(),"Your account has been successfully made.",
                "You can now login in your account with\n email: " + userRequestDTO.getEmail() + "\npassword: " + userRequestDTO.getPassword());
    }

    @Transactional
    public void updateUser(UserRequestDTO userRequestDTO, UUID userUid) {
        User user = getUserByUid(userUid);
        user = userMapper.mapFromUserDTOToUserUpdate(userRequestDTO, user);
        user.setRole(roleService.getByUid(userRequestDTO.getRoleid()));
        userRepository.save(user);
    }
}
