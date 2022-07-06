package com.example.photogram.web;

import com.example.photogram.domain.image.Image;
import com.example.photogram.domain.user.Users;
import com.example.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable long id, Model model) {
        Users users = userService.getUserProfile(id);

        model.addAttribute("users", users);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id) {
        return "user/update";
    }
}
