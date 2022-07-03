package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.service.SubscribeService;
import com.example.photogram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("toUserId = " + toUserId);
        System.out.println("principalDetails = " + principalDetails.getUsers().getId());
        subscribeService.subscribe(toUserId, principalDetails.getUsers().getId());
        return ResponseEntity.ok(new CMRespDto<>("구독 성공", null, 1));
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.unSubscribe(toUserId, principalDetails.getUsers().getId());
        return ResponseEntity.ok(new CMRespDto<>("구독 취소", null, 1));
    }
}
