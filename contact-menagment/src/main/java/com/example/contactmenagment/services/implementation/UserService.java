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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
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

    public ResponseEntity<Page<ContactResponseDTO>> getAllContactsByUserUid(UUID uid, Pageable pageable){
        if(getAccess(uid)){
            return ResponseEntity.ok().body(contactMapper.mapFromEntityList(contactsRepository.findAllByUser_Uid(uid, pageable)));
        }

        return ResponseEntity.badRequest().build();
    }

    public User getUserByUid(UUID uid){

        return userRepository.findUserByUid(uid).orElseThrow(() -> new EntityNotFoundException("No User found!"));
    }

    //UserResponseDTO
    public ResponseEntity<UserResponseDTO> getDTOByUid(UUID uid) {
        if(getAccess(uid)){
            return ResponseEntity.ok().body(userMapper.mapFromUserToUserDTO(userRepository.findUserByUid(uid).orElseThrow(() -> new NoSuchElementException("No User found!"))));
        }
        return ResponseEntity.badRequest().build();
    }

    public void save(UserRequestDTO o) {
        userRepository.save(userMapper.mapFromUserDTOToUser(o));
    }

    public ResponseEntity<UserResponseDTO> updateUser(UUID uid, UserRequestDTO userRequestDTO){
        if(getAccess(uid)){
            User user  = userMapper.mapFromUserDTOToUserUpdate(uid, userRequestDTO);
            userRepository.save(user);
            return ResponseEntity.ok().body(userMapper.mapFromUserToUserDTO(user));
        }
        return ResponseEntity.badRequest().build();
    }
    public User getLoggedInUser() {
        String userEmail = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userEmail = authentication.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login required.");
        }

        final String email = userEmail;

        return userRepository.findUserByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("User with email : " + email + " does not exist"));
    }
    public boolean getAccess(UUID uid){
        User loggedUser = getLoggedInUser();

        User user = getUserByUid(uid);
        if(user.getUid()== loggedUser.getUid() || loggedUser.getRole().getRoleName().equals("ROLE_ADMIN")){
            return true;
        }
        return false;
    }

}
