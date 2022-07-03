package com.example.photogram.service.impl;

import com.example.photogram.domain.subscribe.Subscribe;
import com.example.photogram.domain.user.Users;
import com.example.photogram.handler.exception.CustomApiException;
import com.example.photogram.repository.SubscribeRepository;
import com.example.photogram.repository.UserRepository;
import com.example.photogram.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void subscribe(long toUserId, long fromUserId) {
        Users toUser = userRepository.findById(toUserId).orElseThrow(() -> new CustomApiException("Not Found User::" + toUserId));
        Users fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new CustomApiException("Not Found User:: " + fromUserId));

        Subscribe subscribe = Subscribe.builder()
                .toUser(toUser)
                .fromUser(fromUser)
                .build();

        try{
            subscribeRepository.save(subscribe);
        }catch (Exception e) {
            new CustomApiException("이미 구독하셨습니다.");
        }
    }

    @javax.transaction.Transactional
    @Override
    public void unSubscribe(long toUserId, long fromUserId) {
        subscribeRepository.deleteByToUserIdAndFromUserId(toUserId, fromUserId);

    }
}
