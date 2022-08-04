package com.example.contactmenagment.repository;

import com.example.contactmenagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUid(UUID uid);
    void deleteUserByUid(UUID uid);
}