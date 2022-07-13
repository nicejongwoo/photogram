package com.example.photogram.repository;

import com.example.photogram.domain.subscribe.Subscribe;
import com.example.photogram.domain.user.Users;
import com.example.photogram.web.dto.subscribe.SubscribeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    void deleteByToUserIdAndFromUserId(long toUserId, long fromUserId);

    int countByToUser(Users toUser);

    int countByFromUserAndToUser(Users fromUser, Users toUser);

    String query =
            "select " +
                "u.id as userId, u.username, u.profile_image_url as profileImageUrl, " +
                "(select case when select true from subscribe where from_user_id = :principalId and to_user_id = u.id then 1 else 0 end) as subscribeState, " +
                "(select case when :principalId=u.id then 1 else 0 end) as equalUserState " +
            "from users u\n" +
            "join subscribe s\n" +
            "on u.id = s.to_user_id\n" +
            "where s.from_user_id = :pageUserId";
    @Query(value = query, nativeQuery = true)
    List<SubscribeDto> getFindSubscribeList(Long principalId, int pageUserId);
}
