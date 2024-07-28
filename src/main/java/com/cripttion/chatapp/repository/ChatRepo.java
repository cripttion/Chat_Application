package com.cripttion.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cripttion.chatapp.model.entity.Chat;

import java.util.*;

@Repository
public interface ChatRepo extends JpaRepository<Chat,UUID> {
    
}
