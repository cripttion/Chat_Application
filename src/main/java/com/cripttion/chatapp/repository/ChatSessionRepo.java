package com.cripttion.chatapp.repository;

import com.cripttion.chatapp.model.entity.ChatSessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepo extends JpaRepository<ChatSessions,Long> {

}
