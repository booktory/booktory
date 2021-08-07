package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.notification.NotificationRequestDto;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeNotificationService {

    static final String prefix = "FCM_TOKEN_";
    private final String[] badgeName = {"책이 궁금해", "카리스마 리더", "책토리 회장님", "모임의 시작", "책을 좋아해", "나는야 책 인싸", "책토리 홀릭", "책토리 토박이", "독서에 미친 자"};

    private final UserRepository userRepository;
    private final FCMService fcmService;
    private final RedisUtil redisUtil;

    public void makeBadgeNotification(int badgeId, User user) {
        if (user.isExistBadge(badgeId)) return;

        NotificationRequestDto notificationRequestDto = null;
        String token = redisUtil.getValue(prefix + user.getId());
        user.updateBadgeStatus(badgeId);
        userRepository.save(user);

        notificationRequestDto = NotificationRequestDto.builder()
                .toUserFCMToken(token)
                .title("새로운 배지를 획득했어요 :)")
                .message("'" + badgeName[badgeId] + "' 배지를 새로 획득했어요. 마이페이지에서 확인해 보세요 :)")
                .build();

//        switch(badgeId) {
//            case 0:
//            case 1:
//            case 2:
//                notificationRequestDto = NotificationRequestDto.builder()
//                        .toUserFCMToken(token)
//                        .title("새로운 배지를 획득했어요 :)")
//                        .message("'" + badgeName[badgeId] + "' 배지를 새로 획득했어요. 마이페이지에서 확인해 보세요 :)")
//                        .build();
//                break;
//
//            default:
//                break;
//        }

        fcmService.setFirebaseRealTimeDB(notificationRequestDto, user.getNickname());
        if (token != null) {
            try {
                fcmService.send(notificationRequestDto);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
