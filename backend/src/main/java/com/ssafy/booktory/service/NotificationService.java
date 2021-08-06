package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.notification.NotificationRequestDto;
import com.ssafy.booktory.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ssafy.booktory.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    static final String prefix = "FCM_TOKEN_";

    private final RedisUtil redisUtil;
    private final FCMService fcmService;

    public void registerToken(Long userId, String token) {
        redisUtil.setValue(prefix + userId, token);
    }

//    public String getToken(Long userId) {
//        return redisUtil.getValue(prefix + String.valueOf(userId));
//    }

    public void deleteToken(Long userId) {
        redisUtil.deleteKey(prefix + userId);
    }

    @Transactional
    public void makeNotification(String type, Club club, User user) {
        NotificationRequestDto notificationRequestDto = null;
        String token = null;
        switch(type) {
            case "apply":
                token = redisUtil.getValue(prefix + club.getUser().getId());
                notificationRequestDto = NotificationRequestDto.builder()
                        .toUserFCMToken(token)
                        .title("클럽 가입 신청이 들어왔어요 :)")
                        .message(user.getNickname() + "님이 " + club.getName() + " 클럽에 가입을 신청했어요 :)")
                        .build();
                saveNotification(notificationRequestDto, club.getUser().getNickname());
                break;
            case "accept":
                token = redisUtil.getValue(prefix + user.getId());
                notificationRequestDto = NotificationRequestDto.builder()
                        .toUserFCMToken(token)
                        .title("가입 신청이 승인되었어요 :)")
                        .message(club.getName() + " 클럽에 가입되었어요 :)")
                        .build();
                saveNotification(notificationRequestDto, user.getNickname());
                break;
            case "reject":
                token = redisUtil.getValue(prefix + user.getId());
                notificationRequestDto = NotificationRequestDto.builder()
                        .toUserFCMToken(token)
                        .title("가입 신청이 거절되었어요 :(")
                        .message(club.getName() + " 클럽의 가입 신청이 거절되었어요 :(")
                        .build();
                saveNotification(notificationRequestDto, user.getNickname());
                break;
            case "question":
                token = redisUtil.getValue(prefix + club.getUser().getId());
                notificationRequestDto = NotificationRequestDto.builder()
                        .toUserFCMToken(token)
                        .title("클럽 가입 문의가 들어왔어요 :)")
                        .message(user.getNickname() + "님이 " + club.getName() + " 클럽에 문의를 남겼어요 :)")
                        .build();
                saveNotification(notificationRequestDto, club.getUser().getNickname());
                break;
            case "answer":
                token = redisUtil.getValue(prefix + user.getId());
                notificationRequestDto = NotificationRequestDto.builder()
                        .toUserFCMToken(token)
                        .title("문의에 대한 답변이 달렸어요 :)")
                        .message(club.getName() + " 클럽 문의에 대한 답변이 달렸어요 :)")
                        .build();
                saveNotification(notificationRequestDto, user.getNickname());
                break;
            default:
                break;
        }

        if (token != null) {
            sendNotification(notificationRequestDto);
        }
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
