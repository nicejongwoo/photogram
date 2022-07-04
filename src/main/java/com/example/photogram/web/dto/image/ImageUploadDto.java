package com.example.photogram.web.dto.image;

import com.example.photogram.domain.image.Image;
import com.example.photogram.domain.user.Users;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, Users users) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .users(users)
                .build();
    }

}
