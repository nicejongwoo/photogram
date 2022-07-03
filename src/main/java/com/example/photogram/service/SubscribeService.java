package com.example.photogram.service;

public interface SubscribeService {
    void subscribe(long toUserId, long id);

    void unSubscribe(long toUserId, long id);
}
