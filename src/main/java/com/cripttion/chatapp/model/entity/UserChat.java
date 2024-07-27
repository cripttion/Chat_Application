package com.cripttion.chatapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_chats")
public class UserChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userChatId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "last_read_message_id")
    private Message lastReadMessage;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime joinedAt;

    private Boolean isAdmin;

    // Getters and setters
}
