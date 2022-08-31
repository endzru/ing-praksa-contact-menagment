package com.example.contactmenagment.services;

import com.example.contactmenagment.controllers.dto.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.dto.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.mail.EmailService;
import com.example.contactmenagment.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
public class UserService {

    private  UserRepository userRepository;
    private  RoleService roleService;
    private  UserMapper userMapper;

    private EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService,
                       UserMapper userMapper, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.emailService = emailService;
    }

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
    public void save(UserRequestDTO userRequestDTO) throws MessagingException {
        User user = userMapper.mapFromUserDTOToUser(userRequestDTO);
        user.setRole(roleService.getByUid(userRequestDTO.getRoleid()));
        userRepository.save(user);

        emailService.sendWelcomeMail(userRequestDTO);
    }

    @Transactional
    public void updateUser(UserRequestDTO userRequestDTO, UUID userUid) {
        User user = getUserByUid(userUid);
        //if(user.equals(userRequestDTO))
        user = userMapper.mapFromUserDTOToUserUpdate(userRequestDTO, user);
        user.setRole(roleService.getByUid(userRequestDTO.getRoleid()));
        userRepository.save(user);
    }
}
