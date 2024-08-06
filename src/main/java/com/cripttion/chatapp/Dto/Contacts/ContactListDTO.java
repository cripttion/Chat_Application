package com.cripttion.chatapp.Dto.Contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor

public class ContactListDTO {
    private String alias;
    private UUID contactUserId;
    private String contactUsername;
    private String contactEmail;
    private String contactPhoneNumber;
    private String profilePicture;
    private String status;

    public ContactListDTO(String alias, UUID contactUserId, String contactUsername, String contactEmail, String contactPhoneNumber, String status, String profilePicture) {

        this.alias = alias;
        this.contactUserId = contactUserId;
        this.contactUsername = contactUsername;
        this.contactEmail = contactEmail;
        this.contactPhoneNumber = contactPhoneNumber;
        this.status = status;
        this.profilePicture = profilePicture;
    }
}
