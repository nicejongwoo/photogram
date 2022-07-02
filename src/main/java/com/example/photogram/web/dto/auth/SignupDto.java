package com.example.photogram.web.dto.auth;

import com.example.photogram.domain.user.Users;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
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
