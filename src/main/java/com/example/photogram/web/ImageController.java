package com.example.photogram.web;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.handler.CustomValidationException;
import com.example.photogram.service.ImageService;
import com.example.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("파일이 없습니다.", null);
        }
        imageService.uploadImage(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUsers().getId();
    }

}
