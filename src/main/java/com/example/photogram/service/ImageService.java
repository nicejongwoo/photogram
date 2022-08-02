package com.example.photogram.service;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.image.Image;
import com.example.photogram.web.dto.image.ImageUploadDto;

import java.util.List;

public interface ImageService {
    void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails);

    List<Image> imageStory(Long id);
}
