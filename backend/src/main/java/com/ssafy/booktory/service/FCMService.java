package com.ssafy.booktory.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.ssafy.booktory.domain.notification.FirebaseNotification;
import com.ssafy.booktory.domain.notification.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FCMService {

    private static final Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void send(NotificationRequestDto notificationRequestDto) throws ExecutionException, InterruptedException {
        Message message = Message.builder()
                .setToken(notificationRequestDto.getToUserFCMToken())
                .setWebpushConfig(
                        WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(
                                notificationRequestDto.getTitle(),
                                notificationRequestDto.getMessage()
                        )).build()
                ).build();

        String notificationResponse = FirebaseMessaging.getInstance().sendAsync(message).get();
        logger.info("Sent Message : " + notificationResponse);
    }

    public void setFirebaseRealTimeDB(NotificationRequestDto notificationRequestDto, String nickname) {
        ZonedDateTime nowSeoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime curDate = nowSeoul.toLocalDateTime();
        String formattedDate = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("users").child(nickname);
        DatabaseReference newRef = ref.push();
        newRef.setValueAsync(new FirebaseNotification(false, notificationRequestDto.getMessage(), formattedDate));
    }

}
