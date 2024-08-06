package com.cripttion.chatapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private String chatID;
    private String sender;
    private String message;
    private String timestamp;
    private boolean seen;

    @Override
    public String toString() {
        return "MessageDTO{" +
                "chatID='" + chatID + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", seen=" + seen +
                '}';
    }
}
