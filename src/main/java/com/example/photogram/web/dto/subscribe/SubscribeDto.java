package com.example.photogram.web.dto.subscribe;

//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public interface SubscribeDto {
    int getUserId();

    String getUsername();

    String getProfileImageUrl();

    Integer getSubscribeState();

    Integer getEqualUserState();

}
