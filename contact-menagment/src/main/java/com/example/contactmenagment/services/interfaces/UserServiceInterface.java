package com.example.contactmenagment.services.interfaces;


import com.example.contactmenagment.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserServiceInterface {

    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User s);

    User updateUser(User s);

    User deleteUserById(Long id);




}
