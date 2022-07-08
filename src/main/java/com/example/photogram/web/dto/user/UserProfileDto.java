package com.example.photogram.web.dto.user;

import com.example.photogram.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

    private boolean ownerPageState;
    private int imageCount;
    private Users users;

}
