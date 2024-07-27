package com.cripttion.chatapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(path = "/user")
    public UserDto createUser(@RequestBody UserDto userDto)
    {
   
      User user = userMapper.mapFrom(userDto);
      User savedUserEntity = userService.createUser(user);
      return userMapper.mapTo(savedUserEntity);
    }
}
