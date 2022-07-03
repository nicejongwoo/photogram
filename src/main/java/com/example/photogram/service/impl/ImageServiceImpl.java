package com.example.photogram.service.impl;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.repository.ImageRepository;
import com.example.photogram.service.ImageService;
import com.example.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String FILE_PATH;

    @Override
    public void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        String originalFilename = imageUploadDto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        String imageFileName = uuid + "_" + originalFilename;
        Path imageFilePath = Paths.get(FILE_PATH + imageFileName);

        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
