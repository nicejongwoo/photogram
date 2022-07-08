package com.example.photogram.repository;

import com.example.photogram.domain.subscribe.Subscribe;
import com.example.photogram.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    void deleteByToUserIdAndFromUserId(long toUserId, long fromUserId);

    int countByToUser(Users toUser);

    int countByFromUserAndToUser(Users fromUser, Users toUser);
}
