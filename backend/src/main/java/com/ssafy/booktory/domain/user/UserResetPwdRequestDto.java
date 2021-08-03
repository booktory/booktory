package com.ssafy.booktory.domain.user;

import lombok.Getter;

@Getter
public class UserResetPwdRequestDto {

    private String email;
    private String password;

}
