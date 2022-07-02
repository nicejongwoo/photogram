package com.example.photogram.service.impl;

import com.example.photogram.domain.user.Users;
import com.example.photogram.handler.CustomValidationApiException;
import com.example.photogram.repository.UserRepository;
import com.example.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users createUser(Users users) {
        Users userEntity = userRepository.save(users);
        return userEntity;
    }

    @Transactional
    @Override
    public Users update(long id, Users users) {

        //persistent
        Users usersEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("회원 정보를 찾을 수 없습니다."));

        //update (dirty checking)
        usersEntity.setName(users.getName());
        usersEntity.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersEntity.setBio(users.getBio());
        usersEntity.setGender(users.getGender());
        usersEntity.setPhone(users.getPhone());
        usersEntity.setWebsite(users.getWebsite());

        return usersEntity;
    }
}
