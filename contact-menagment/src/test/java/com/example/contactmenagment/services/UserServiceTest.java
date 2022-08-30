package com.example.contactmenagment.services;

import com.example.contactmenagment.controllers.dto.userDTO.UserResponseDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @InjectMocks
    @Autowired
    UserService userService;

    @Test
    void TestGetUsersPage(){
        User user = new User();
        user.setContacts(null);
        user.setUid(UUID.randomUUID());
        user.setEmail("astojanovic321@gmail.com");
        user.setFirstName("Andrija");
        user.setLastName("Stojanovic");
        user.setRole(null);
        user.setTimeCreated(null);
        user.setTimeUpdated(null);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        Pageable pageable = Pageable.ofSize(1);
        Page<User> pagelist = new PageImpl<>(userList);

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getUid(), user.getEmail(),user.getFirstName(),
                user.getLastName());
        List<UserResponseDTO> listUserResponseDTO = new ArrayList<>();
        listUserResponseDTO.add(userResponseDTO);

        Page<UserResponseDTO> userResponseDTOS = new PageImpl<>(listUserResponseDTO);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(pagelist);
        Assertions.assertEquals(userResponseDTOS.getContent().get(0).getUid(),
                userService.getAll(pageable).getContent().get(0).getUid());
    }

}
