package com.example.photogram.web;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable long pageUserId,
                          @AuthenticationPrincipal PrincipalDetails principalDetails,
                          Model model) {
        UserProfileDto profileDto = userService.getUserProfile(pageUserId, principalDetails);

        model.addAttribute("profileDto", profileDto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id) {
        return "user/update";
    }
}
