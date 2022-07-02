package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.user.Users;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.CMRespDto;
import com.example.photogram.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable long id, UserRequestDto userRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users usersEntity = userService.update(id, userRequestDto.toEntity());
        principalDetails.setUsers(usersEntity);

        return new CMRespDto<>("success update", usersEntity, 1);
    }

}
