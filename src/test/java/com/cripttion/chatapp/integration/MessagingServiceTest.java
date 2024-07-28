package com.cripttion.chatapp.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cripttion.chatapp.model.entity.Chat;
import com.cripttion.chatapp.model.entity.Message;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.model.entity.UserChat;
import com.cripttion.chatapp.model.enums.ChatType;
import com.cripttion.chatapp.model.enums.MessageType;
import com.cripttion.chatapp.repository.ChatRepo;
import com.cripttion.chatapp.repository.MessageRepo;
import com.cripttion.chatapp.repository.UserChatRepo;
import com.cripttion.chatapp.repository.UserRepo;
import com.cripttion.chatapp.service.chatservice.MessagingService;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class MessagingServiceTest {
    
    @Mock
    @Autowired
    private UserRepo userRepo;

    @Mock
    @Autowired
    private ChatRepo chatRepo;

    @Mock 
    @Autowired
    private UserChatRepo userChatRepo;

    @Mock
    @Autowired
    private MessageRepo messageRepo;

    @InjectMocks
    @Autowired
    private MessagingService messagingService;

    private User sender;
    private User receiver;
    private Chat chat;
    private UUID senderID;
    private UUID receiverId;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        
        senderID =UUID.randomUUID();
        receiverId = UUID.randomUUID();

        sender = new User();
        sender.setUserId(senderID);

        receiver = new User();
        receiver.setUserId(receiverId);

        chat = new Chat();
        chat.setChatType(ChatType.PRIVATE);


        
    }

   @Test
   public void testSendMessage_FirstMessage() {
        String content = "Hello, this is the first message!";
        
        // Mocking the userRepo responses
        when(userRepo.findAllByuserId(senderID)).thenReturn(sender);
        when(userRepo.findAllByuserId(receiverId)).thenReturn(receiver);

        // Mocking the chatRepo response
        when(chatRepo.save(any(Chat.class))).thenReturn(chat);

        // Mocking the userChatRepo response
        when(userChatRepo.findByUserAndChat(any(User.class), any(Chat.class)))
                .thenReturn(null);

        // Mocking the MessageRepository response
       Message savedMessage = new Message();
        savedMessage.setMessageId(UUID.randomUUID());
        when(messageRepo.save(any(Message.class))).thenReturn(savedMessage);

        // Execute the method under test
        Message result = messagingService.sendMessage(senderID,receiverId,content,MessageType.TEXT);

        // Verifications and assertions
        verify(userRepo).findAllByuserId(senderID);
        verify(userRepo).findAllByuserId(receiverId);
        verify(chatRepo).save(any(Chat.class));
        verify(messageRepo).save(any(Message.class));
        verify(userChatRepo,times(4)).save(any(UserChat.class));

        assertNotNull(result);
        // assertEquals(savedMessage.getMessageId(), result.getMessageId());
        assertEquals(content, result.getContent());
    }
    
}
