package com.cripttion.chatapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cripttion.chatapp.model.entity.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message,UUID> {
    
}
