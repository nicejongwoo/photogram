package com.example.photogram.service;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.user.Users;
import com.example.photogram.web.dto.user.UserProfileDto;
import com.example.photogram.web.dto.user.UserRequestDto;

public interface UserService {
    Users createUser(Users users);
    Users update(long id, Users users);

    UserProfileDto getUserProfile(long id, PrincipalDetails principalDetails);
}
