package com.ssafy.booktory.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseNotification {

    private String message;
    private String createDateTime;

}
