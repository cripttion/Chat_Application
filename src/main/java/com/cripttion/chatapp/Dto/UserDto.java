package com.cripttion.chatapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;



import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    
    private UUID userId;

    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String profilePicture;
    private String status;

    
    private LocalDateTime lastSeen;

    
    private LocalDateTime createdAt;

    
    private LocalDateTime updatedAt;

    
    
}
