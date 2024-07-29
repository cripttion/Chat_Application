package com.cripttion.chatapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    // @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String profilePicture;
    private String status;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime lastSeen;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    // Relationships
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserChat> userChats;

    @OneToMany(mappedBy = "sender")
    private Set<Message> messages;

    @OneToMany(mappedBy = "owner")
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;
}
