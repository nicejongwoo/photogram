package com.example.photogram.web.dto.auth;

import com.example.photogram.domain.user.Users;
import lombok.Data;

@Data
public class SignupDto {

    private String username;
    private String password;
    private String email;
    private String name;

    public Users toEntity() {
        return Users.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }

}
