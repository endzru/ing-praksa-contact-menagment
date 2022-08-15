package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.controllers.contactDTO.ContactResponseDTO;
import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.mappers.ContactMapper;
import com.example.contactmenagment.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userMapper.mapFromEntityList(userRepository.findAll(pageable));
    }

    public Page<ContactResponseDTO> getAllContactsByUserUid(UUID uid, Pageable pageable){
            return contactMapper.mapFromEntityList(contactsRepository.findAllByUser_Uid(uid, pageable));
    }

    public User getUserByUid(UUID uid){

        return userRepository.findUserByUid(uid).orElseThrow(() -> new EntityNotFoundException("No User found!"));
    }

    public ResponseEntity<UserResponseDTO> getDTOByUid(UUID uid) {
            return ResponseEntity.ok().body(userMapper.mapFromUserToUserDTO(userRepository.findUserByUid(uid).orElseThrow(() -> new NoSuchElementException("No User found!"))));
    }

    public void save(UserRequestDTO o) {
        userRepository.save(userMapper.mapFromUserDTOToUser(o));
    }

    public UserResponseDTO updateUser(UUID uid, UserRequestDTO userRequestDTO){
            User user  = userMapper.mapFromUserDTOToUserUpdate(uid, userRequestDTO);
            userRepository.save(user);
            return userMapper.mapFromUserToUserDTO(user);
    }

}
