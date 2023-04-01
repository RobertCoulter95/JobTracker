package com.example.jobtracker.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {
    public static final int EXPIRATION = 60 * 24;
    public PasswordResetToken(String token, String username){
        this.username = username;
        this.token = token;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private String username;

    private LocalDateTime expiryDate;

    public boolean isExpired(){
        return expiryDate.isAfter(LocalDateTime.now());
    }
}
