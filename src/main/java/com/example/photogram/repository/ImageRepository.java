package com.example.photogram.repository;

import com.example.photogram.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(
            value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId)",
            nativeQuery = true
    )
    List<Image> mStory(Long principalId);
}
