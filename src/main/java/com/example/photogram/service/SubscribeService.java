package com.example.photogram.service;

import com.example.photogram.web.dto.subscribe.SubscribeDto;

import java.util.List;

public interface SubscribeService {
    void subscribe(long toUserId, long id);

    void unSubscribe(long toUserId, long id);

    List<SubscribeDto> getSubscribeList(Long id, int pageUserId);
}
