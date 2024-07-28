
package com.cripttion.chatapp.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cripttion.chatapp.Dto.GetingMessageRequest;
import com.cripttion.chatapp.model.entity.Message;
import com.cripttion.chatapp.model.enums.MessageType;
import com.cripttion.chatapp.service.chatservice.MessagingService;

@RestController
@RequestMapping("/v1/api/messages")
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody GetingMessageRequest messagePayload) {
        System.out.println(messagePayload);
        MessageType messageType;
    switch (messagePayload.getType().toLowerCase()) {
        case "text":
            messageType = MessageType.TEXT;
            break;
        case "audio":
            messageType = MessageType.AUDIO;
            break;
        case "video":
            messageType = MessageType.VIDEO;
            break;
        case "image":
            messageType = MessageType.IMAGE;
            break;
        case "file":
            messageType = MessageType.FILE;
            break;
        default:
            throw new IllegalArgumentException("Invalid message type: " + messagePayload.getType());
    }
        return new ResponseEntity<>(
            messagingService.sendMessage(messagePayload.getSenderId(), messagePayload.getReceiverID(), messagePayload.getContent(),messageType),
            HttpStatus.OK
            
        );
    }
}
