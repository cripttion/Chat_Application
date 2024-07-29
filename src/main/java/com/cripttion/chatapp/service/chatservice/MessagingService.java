package com.cripttion.chatapp.service.chatservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import com.cripttion.chatapp.model.entity.Chat;
import com.cripttion.chatapp.model.entity.Media;
import com.cripttion.chatapp.model.entity.Message;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.model.entity.UserChat;
import com.cripttion.chatapp.model.enums.ChatType;
import com.cripttion.chatapp.model.enums.MessageType;
import com.cripttion.chatapp.repository.ChatRepo;
import com.cripttion.chatapp.repository.MessageRepo;
import com.cripttion.chatapp.repository.UserChatRepo;
import com.cripttion.chatapp.repository.UserRepo;

import lombok.Data;

@Data
@Service
public class MessagingService {
    
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserChatRepo userChatRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;


    @Transactional
    public Message sendMessage(UUID senderID , UUID receiverID , String Content ,MessageType messageType){
        //geting the dat of sender and receiver using its user_Id
        User sender = userRepo.findAllByuserId(senderID);
        User receiver = userRepo.findAllByuserId(receiverID);
        
        //Checking & creating achat
        Chat chat = findOrCreateChat(sender,receiver);

       
        //creating New Message table
        Message newMessage = new Message();
        newMessage.setChat(chat);
        if(messageType!=MessageType.TEXT)
        {
            newMessage.setMediaUrl(Content);    
        }else{

            newMessage.setContent(Content);
        }
        newMessage.setSender(sender);
        newMessage.setMessageType(messageType);
        newMessage.setCreatedAt(LocalDateTime.now());

        messageRepo.save(newMessage);

        updateUserChat(receiver, chat, newMessage);
        updateUserChat(sender, chat, newMessage);

        return newMessage;
    }


    private Chat findOrCreateChat(User sender , User receiver)
    {

        Set<UserChat> userChats = userChatRepo.findByUser(sender);
        System.out.println(userChats);
        for(UserChat userchat:userChats)
        {

            if(userchat.getChat().getChatType()==ChatType.PRIVATE)
            {
                Set<UserChat> userChats1 = userChatRepo.findByChat(userchat.getChat());
                for(UserChat uc:userChats1)
                {
                    if(uc.getUser().equals(receiver))
                    {
                         return userchat.getChat();
                    }
                }   
            }
        }
       Chat newChat = new Chat();
       newChat.setChatType(ChatType.PRIVATE);
       newChat.setCreatedAt(LocalDateTime.now());
       newChat.setUpdatedAt(LocalDateTime.now());
       chatRepo.save(newChat);

       //createing chat for sender and receiver
       createUserChat(sender,newChat);
       createUserChat(receiver,newChat);

       return newChat;
    }


    private void createUserChat(User user,Chat chat)
    {
        UserChat userChat = new UserChat();
        userChat.setUser(user);
        userChat.setChat(chat);
        userChatRepo.save(userChat);
    }

    private void updateUserChat(User user, Chat chat, Message lastReadMessage)
    {
        UserChat userChat = userChatRepo.findByUserAndChat(user, chat);
        if(userChat==null){
            userChat = new UserChat();
            userChat.setChat(chat);
            userChat.setUser(user);
        }
        userChat.setLastReadMessage(lastReadMessage);
        userChatRepo.save(userChat);
    }
}
