package com.ssafy.booktory.domain.notification;

import lombok.Getter;

@Getter
public class FCMRequestDto {

    private String email;
    private String token;
}
