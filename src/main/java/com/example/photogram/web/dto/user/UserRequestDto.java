package com.example.photogram.web.dto.user;

import com.example.photogram.domain.user.Users;
import lombok.Data;

@Data
public class UserRequestDto {

    private String name;
    private String password;
    private String bio;
    private String website;
    private String phone;
    private String gender;

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
