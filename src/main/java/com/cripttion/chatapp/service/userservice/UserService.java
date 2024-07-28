package com.cripttion.chatapp.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.repository.UserRepo;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
   
    public UserService (UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    public boolean isExist(UUID id){
        return userRepo.existsByuserId(id);
    }

     //adding new user to databse
    public User createUser(User user){
        userRepo.save(user);
        return user;
    }
    
    //Get he details of Specific User
    public User getUserWithUserID(UUID id)
    {
       return userRepo.findAllByuserId(id);
    
    }
   
    //Update the UserName of a specific user 
    public User updateUserName(UUID id,User user)
    {
            user.setUserId(id);
           return userRepo.findById(id).map(existingUser->{
                Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);

                return userRepo.save(existingUser);
            }).orElseThrow(()->new RuntimeException("User doesn't Exits"));
    }

    //Delete the user with the userID
    public String deleteUser(UUID id)
    {
       try {
        userRepo.deleteById(id);
        return "User Deleted Sucessfully";
       } catch (Exception e) {
        
        return "Unable to delete";
       }
        
       
    }
}
