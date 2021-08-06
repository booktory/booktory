package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.notification.NotificationRequestDto;
import com.ssafy.booktory.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    static final String prefix = "FCM_TOKEN_";

    private final RedisUtil redisUtil;
    private final FCMService fcmService;

    public void registerToken(Long userId, String token) {
        redisUtil.setValue(prefix + String.valueOf(userId), token);
    }

    public String getToken(Long userId) {
        return redisUtil.getValue(prefix + String.valueOf(userId));
    }

    public void deleteToken(Long userId) {
        redisUtil.deleteKey(prefix + String.valueOf(userId));
    }

    public void sendNotification(NotificationRequestDto notificationRequestDto) {
        try {
            fcmService.send(notificationRequestDto);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void saveNotification(NotificationRequestDto notificationRequestDto, String nickname) {
        fcmService.setFirebaseRealTimeDB(notificationRequestDto, nickname);
    }





}
