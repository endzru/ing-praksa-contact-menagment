package com.example.contactmenagment.services;

import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.implementation.UserService;
import com.example.contactmenagment.services.mappers.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserMapper userMapper;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    void testGetUsers(){
        List<User> asd = new ArrayList<>();

        UserRequestDTO urd = new UserRequestDTO("astojanovic321@gmail.com",
                "Andrija", "Stojanovic",
                "Andrija2001", UUID.randomUUID());

        asd.add(userMapper.mapFromUserDTOToUser(urd));

        Mockito.when(userRepository.findAll()).thenReturn(asd);
        Assertions.assertEquals(asd, userService.getAllusers());
    }
}
