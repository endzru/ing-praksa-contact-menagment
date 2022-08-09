package com.example.contactmenagment.services.mappers;


import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.Role;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.ContactsRepository;
import com.example.contactmenagment.repository.RoleRepository;
import com.example.contactmenagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public List<UserResponseDTO> mapFromUserToUserDTO(List<User> userList){
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for (User u: userList) {
            UserResponseDTO userDto = new UserResponseDTO();
            userDto.setUid(u.getUid());
            userDto.setEmail(u.getEmail());
            userDto.setFirstName(u.getFirstName());
            userDto.setLastName(u.getLastName());
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
        User user = new User();
        user.setUid(UUID.randomUUID());
        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(new Role(6L, "user", userRequestDTO.getRoleid()));
        return user;
    }
    public User mapFromUserDTOToUserUpdate(UUID uid, UserRequestDTO userRequestDTO){
        User user =userRepository.findUserByUid(uid).orElseThrow(()-> new EntityNotFoundException("No user found!"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(roleRepository.getRoleByUid(userRequestDTO.getRoleid())
                .orElseThrow(()->new EntityNotFoundException("NO role found!")));

        return user;
    }
    public Page<UserResponseDTO> mapFromEntityList(Page<User> userPage){
        return userPage.map(this::mapFromUserToUserDTO);
    }
}
