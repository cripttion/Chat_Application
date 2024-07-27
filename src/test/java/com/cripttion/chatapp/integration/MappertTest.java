package com.cripttion.chatapp.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cripttion.chatapp.Dto.UserDto;
import com.cripttion.chatapp.mapper.impl.UserMapperimp;
import com.cripttion.chatapp.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MappertTest {

    @Autowired
    private UserMapperimp userMapper;

    @Test
    public void whenMapUserWithExactMatch_thenConvertsToDTO() {
        // Arrange
        User user = new User(null, "rahul", "2309409", null, null, null, null, null, null, null, null, null, null);

        // Act
        UserDto userDTO = userMapper.mapTo(user);

        // Assert
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
    }
}
