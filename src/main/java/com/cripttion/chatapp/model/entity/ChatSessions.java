package com.cripttion.chatapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "ChatSessions")
public class ChatSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatSessionId;

    private UUID chatId;
    private String connectionID;
    private UUID user;


}
