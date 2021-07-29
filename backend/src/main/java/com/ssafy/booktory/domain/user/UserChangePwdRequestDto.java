package com.ssafy.booktory.domain.user;

import lombok.Getter;

@Getter
public class UserChangePwdRequestDto {

    private String current_password;
    private String new_password;
}
