package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No User for ID: " + id + " found!"));
    }

    @Override
    public User saveUser(User s) {
        return userRepository.save(s);
    }

    @Override
    public User updateUser(User s) {
        return userRepository.save(s);
    }

    @Override
    public User deleteUserById(Long id) {
        User temp = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No User for ID: " + id + " found!"));
        userRepository.deleteById(id);
        return temp;
    }
}
