package com.example.contactmenagment.services.mappers;


import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    public List<UserResponseDTO> mapFromUserToUserDTO(List<User> userList){
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for (User u: userList) {
            UserResponseDTO userDto = mapFromUserToUserDTO(u);
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
        return usrDto;
    }
    public User mapFromUserDTOToUser(UserRequestDTO userRequestDTO){
        User user = mapUser(userRequestDTO);
        user.setUid(UUID.randomUUID());

        return user;
    }
    public User mapFromUserDTOToUserUpdate(UserRequestDTO userRequestDTO){
        User user = mapUser(userRequestDTO);

        return user;
    }

    private User mapUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return user;
    }

    public Page<UserResponseDTO> mapFromEntityList(Page<User> userPage){
        return userPage.map(this::mapFromUserToUserDTO);
    }
}
