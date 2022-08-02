package com.example.contactmenagment.services.serviceInterface;

import com.example.contactmenagment.entity.User;

import java.util.List;

public interface UserServiceInterface {

    //GET
    List<User> findAllUser();
    User getUserById(Integer id);

    //POST
    User saveUser(User u);

    //PUT
    User updateUser(User u);

    //DELETE
    void deleteUserById(Integer id);

}
