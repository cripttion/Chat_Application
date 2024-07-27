package com.cripttion.chatapp.mapper.impl;

import com.cripttion.chatapp.Dto.UserDto;
import com.cripttion.chatapp.mapper.Mapper;
import com.cripttion.chatapp.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapperimp implements Mapper<User,UserDto> {
    

    private ModelMapper modalMapper;
   
     public UserMapperimp(ModelMapper modelMapper)
     {
         this.modalMapper = modelMapper;
     }
    @Override
    public UserDto mapTo(User user){
      return  modalMapper.map(user,UserDto.class);
    }
    @Override
    public User mapFrom(UserDto userDto)
    {
        return modalMapper.map(userDto,User.class);
    }
}
