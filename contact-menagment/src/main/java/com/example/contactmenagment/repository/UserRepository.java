package com.example.contactmenagment.repository;


import com.example.contactmenagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUid(UUID uid);
    void deleteUserByUid(UUID uid);
    Optional<User> findUserByEmail(String email);

}