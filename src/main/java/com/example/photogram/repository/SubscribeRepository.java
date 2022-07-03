package com.example.photogram.repository;

import com.example.photogram.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    void deleteByToUserIdAndFromUserId(long toUserId, long fromUserId);
}
