package com.ssafy.booktory.domain.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NotificationRequestDto {

    private String toUserFCMToken;
    private String title;
    private String message;

    @Builder
    public NotificationRequestDto(String toUserFCMToken, String title, String message) {
        this.toUserFCMToken = toUserFCMToken;
        this.title = title;
        this.message = message;
    }
}
