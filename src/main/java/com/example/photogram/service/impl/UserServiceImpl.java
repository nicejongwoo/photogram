package com.example.photogram.service.impl;

import com.example.photogram.domain.user.Users;
import com.example.photogram.repository.UserRepository;
import com.example.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users createUser(Users users) {
        Users userEntity = userRepository.save(users);
        return userEntity;
    }
}
