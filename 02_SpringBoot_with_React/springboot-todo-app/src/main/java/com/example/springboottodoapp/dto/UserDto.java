package com.example.springboottodoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String token;
    private String id;
    private String username;
    private String email;
    private String password;

    @Builder
    public UserDto(String token, String id, String username, String email, String password) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
