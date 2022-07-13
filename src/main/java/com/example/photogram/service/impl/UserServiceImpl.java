package com.example.photogram.service.impl;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.user.Users;
import com.example.photogram.handler.CustomValidationApiException;
import com.example.photogram.handler.exception.CustomException;
import com.example.photogram.repository.SubscribeRepository;
import com.example.photogram.repository.UserRepository;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
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

    @Override
    public UserProfileDto getUserProfile(long pageUserId, PrincipalDetails principalDetails) {

        Users toUser = userRepository.findById(pageUserId).orElseThrow(() -> new CustomException("해당하는 유저가 없습니다."));

        Users fromUser = userRepository.findById(principalDetails.getUsers().getId()).orElseThrow(() -> new CustomException("해당하는 유저가 없습니다."));

        long subscribeCount = subscribeRepository.countByFromUser(toUser);
        long subscribeState = subscribeRepository.countByFromUserAndToUser(fromUser, toUser);

        return UserProfileDto.builder()
                .ownerPageState(pageUserId == principalDetails.getUsers().getId())
                .imageCount(toUser.getImages().size())
                .subscribeState(subscribeState == 1)
                .subscribeCount((int) subscribeCount)
                .users(toUser)
                .build();

    }
}
