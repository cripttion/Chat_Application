package com.cripttion.chatapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

import com.cripttion.chatapp.model.enums.ChatType;
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    // Relationships
    @OneToMany(mappedBy = "chat")
    private Set<UserChat> userChats;

    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;

    @OneToOne(mappedBy = "chat")
    private GroupChat groupChat;

    // Getters and setters
}
