package com.example.photogram.web;

import com.example.photogram.domain.user.Users;
import com.example.photogram.handler.CustomValidationException;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

        Map<String, Object> errorMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검증 실패", errorMap);
        } else {
            Users users = signupDto.toEntity();
            users.setPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));
            Users userEntity = userService.createUser(users);
            return "redirect:/auth/signin";
        }

    }

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

}
