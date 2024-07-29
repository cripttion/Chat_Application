package com.cripttion.chatapp.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cripttion.chatapp.Dto.UserDto;
import com.cripttion.chatapp.mapper.Mapper;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.service.userservice.UserService;

import lombok.Data;

@Data

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    private Mapper<User,UserDto> userMapper;

    public UserController(UserService userService,Mapper<User,UserDto>userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

   @GetMapping(path="/user/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("id") UUID id)
    {
         if(!userService.isExist(id))
         {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         User savedUserData = userService.getUserWithUserID(id);
      return new ResponseEntity<>(
              userMapper.mapTo(savedUserData),
                HttpStatus.OK
      );
    }

    @GetMapping(path = "/user/temp/{id}")
    public ResponseEntity<User> getAllUserCorrespondance(@PathVariable("id") UUID id)
    {
        if(!userService.isExist(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User getUserData = userService.getUserWithUserID(id);
        return new ResponseEntity<>(
                getUserData,
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/user")
    public UserDto createUser(@RequestBody UserDto userDto)
    {
   
      User user = userMapper.mapFrom(userDto);
      User savedUserEntity = userService.createUser(user);
      return userMapper.mapTo(savedUserEntity);
    }

    @PatchMapping(path = "/changeUserName/{id}")
    public ResponseEntity<UserDto> changeUserName(@PathVariable("id") UUID userID,@RequestBody UserDto userDto) {
        
      if(!userService.isExist(userID))
      {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      User user = userMapper.mapFrom(userDto);
      User savedUserData = userService.updateUserName(userID, user);
      return new ResponseEntity<>(
              userMapper.mapTo(savedUserData),
                HttpStatus.OK
      );
    }

    @DeleteMapping(path="/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id)
    {
         if(!userService.isExist(id))
         {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>( 
          userService.deleteUser(id),
          HttpStatus.OK
         );
    }
}
