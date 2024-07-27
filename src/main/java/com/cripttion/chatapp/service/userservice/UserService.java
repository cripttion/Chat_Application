package com.cripttion.chatapp.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
   
    public UserService (UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    public User createUser(User user){
        userRepo.save(user);
        return user;
        
    }
}
