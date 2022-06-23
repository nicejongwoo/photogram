package com.example.photogram.web;

import com.example.photogram.domain.user.Users;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("/auth/signup")

    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        Users users = signupDto.toEntity();
        Users userEntity = userService.createUser(users);
        return "redirect:/auth/signin";
    }

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

}
