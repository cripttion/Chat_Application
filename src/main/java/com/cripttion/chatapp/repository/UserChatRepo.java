package com.cripttion.chatapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cripttion.chatapp.model.entity.Chat;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.model.entity.UserChat;

@Repository
public interface UserChatRepo extends JpaRepository<UserChat,UUID> {

    UserChat findByUserAndChat(User user, Chat chat);
}
