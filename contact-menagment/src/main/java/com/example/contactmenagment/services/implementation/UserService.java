package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.repository.UserRepository;

import com.example.contactmenagment.services.mappers.ContactMapper;
import com.example.contactmenagment.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final ContactsRepository contactsRepository;

    private final UserMapper userMapper;
    private final ContactMapper contactMapper;

    @Transactional
    public void deleteByUid(UUID uid) {
        userRepository.deleteUserByUid(uid);
    }

    public List<UserResponseDTO> getAll() {
        return userMapper.mapFromUserToUserDTO(userRepository.findAll());
    }

    public List<ContactResponseDTO> getAllContactsByUserUid(UUID uid){
         return contactMapper.mapFromEntityToDTO(contactsRepository.findAllByUser_Uid(uid));
    }

    public User getBYUid(UUID uid){
        return userRepository.findUserByUid(uid).orElseThrow(() -> new NoSuchElementException("No User found!"));
    }


    public UserResponseDTO getByUid(UUID uid) {
        UserResponseDTO usr = userMapper.mapFromUserToUserDTO(userRepository.findUserByUid(uid).orElseThrow(() -> new NoSuchElementException("No User found!")));
        return usr;
    }


    public void save(UserRequestDTO o) {
        userRepository.save(userMapper.mapFromUserDTOToUser(o));
    }

}
