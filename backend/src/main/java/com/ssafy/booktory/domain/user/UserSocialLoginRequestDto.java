package com.ssafy.booktory.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSocialLoginRequestDto {

    private String email;
    private String socialType;

    public User toEntity(String socialType) {
        return User.builder()
                .nickname("booktory")
                .email(email)
                .password("0000")
                .mainBadge(-1)
                .isAccept(true)
                .socialType(SocialType.valueOf(socialType))
                .build();
    }

}
