package com.example.contactmenagment.services.implementation;

import com.example.contactmenagment.entity.User;
import com.example.contactmenagment.repository.UserRepository;
import com.example.contactmenagment.services.interfaces.ServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements ServicesInterface<User> {

    private final UserRepository userRepository;

    @Override
    public void deleteByUid(UUID uid) {
        userRepository.deleteUserByUid(uid);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUid(UUID uid) {
        return userRepository.findUserByUid(uid);
    }

    @Override
    public User save(User o) {
        return userRepository.save(o);
    }


}
