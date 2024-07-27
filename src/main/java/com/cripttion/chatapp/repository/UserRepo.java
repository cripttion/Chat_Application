package com.cripttion.chatapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cripttion.chatapp.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,UUID> {

   
} 