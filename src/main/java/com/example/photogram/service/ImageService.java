package com.example.photogram.service;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.web.dto.image.ImageUploadDto;

public interface ImageService {
    void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails);
}
