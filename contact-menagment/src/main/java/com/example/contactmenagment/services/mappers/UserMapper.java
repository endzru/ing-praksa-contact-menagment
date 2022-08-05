package com.example.contactmenagment.services.mappers;


import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ContactsRepository contactsRepository;

    private final ContactMapper contactMapper;

    public List<UserResponseDTO> mapFromUserToUserDTO(List<User> userList){
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for (User u: userList) {
            UserResponseDTO userDto = new UserResponseDTO();
            userDto.setUid(u.getUid());
            userDto.setEmail(u.getEmail());
            userDto.setFirstName(u.getFirstName());
            userDto.setLastName(u.getLastName());
            userDto.setContacts(contactMapper.mapFromEntityToDTO(contactsRepository.findAllByUser_Uid(u.getUid())));
            dtoList.add(userDto);
        }

        return dtoList;
    }

    public UserResponseDTO mapFromUserToUserDTO(User user){
        UserResponseDTO usrDto = new UserResponseDTO();
        usrDto.setUid(user.getUid());
        usrDto.setEmail(user.getEmail());
        usrDto.setFirstName(user.getFirstName());
        usrDto.setLastName(user.getLastName());
        usrDto.setContacts(contactMapper.mapFromEntityToDTO(contactsRepository.findAllByUser_Uid(user.getUid())));
        return usrDto;
    }
    public User mapFromUserDTOToUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUid(UUID.randomUUID());
        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(new Role(6L, "user", userRequestDTO.getRoleid()));
        return user;
    }
}
