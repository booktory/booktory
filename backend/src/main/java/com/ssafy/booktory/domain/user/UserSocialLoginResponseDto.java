package com.ssafy.booktory.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSocialLoginResponseDto {

    private String jwt;
    private Long id;
    private String nickname;
    private String email;
    private Boolean isJoinUser;

}
