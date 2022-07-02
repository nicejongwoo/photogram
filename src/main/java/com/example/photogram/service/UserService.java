package com.example.photogram.service;

import com.example.photogram.domain.user.Users;
import com.example.photogram.web.dto.user.UserRequestDto;

public interface UserService {
    Users createUser(Users users);
    Users update(long id, Users users);

}
