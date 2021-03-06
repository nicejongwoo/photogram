package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.user.Users;
import com.example.photogram.handler.CustomValidationApiException;
import com.example.photogram.handler.CustomValidationException;
import com.example.photogram.service.SubscribeService;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.CMRespDto;
import com.example.photogram.web.dto.subscribe.SubscribeDto;
import com.example.photogram.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int pageUserId,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDto = subscribeService.getSubscribeList(principalDetails.getUsers().getId(), pageUserId);

        return new ResponseEntity<>(new CMRespDto<>("success", subscribeDto, 1), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable long id, @Valid UserRequestDto userRequestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            throw new CustomValidationApiException("????????? ?????? ??????", errorMap);
        } else {
            Users usersEntity = userService.update(id, userRequestDto.toEntity());
            principalDetails.setUsers(usersEntity);
            return new CMRespDto<>("success update", usersEntity, 1);
        }


    }

}
