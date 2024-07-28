package com.cripttion.chatapp.Dto;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GetingMessageRequest {
    private UUID senderId;
    private UUID receiverID;
    private String content;
    private String type;
}
