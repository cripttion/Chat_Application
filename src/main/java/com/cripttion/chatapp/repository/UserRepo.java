package com.cripttion.chatapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cripttion.chatapp.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,UUID> {

   User findAllByuserId(UUID userId);
   boolean existsByuserId(UUID userId);

  Optional<User> findByphoneNumber(String mobileNumber);
}