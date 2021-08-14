package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.notification.NotificationRequestDto;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import com.ssafy.booktory.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ssafy.booktory.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    static final String prefix = "FCM_TOKEN_";
    private final String[] badgeName = {"책이 궁금해", "카리스마 리더", "책토리 회장님", "모임의 시작", "책을 좋아해", "나는야 책 인싸", "책토리 홀릭", "책토리 토박이", "독서에 미친 자", "독서는 습관", "자타공인 책덕후", "책토리 중독", "첫 글의 설렘", "소통의 달인", "독서 나눔"};

    private final RedisUtil redisUtil;
    private final FCMService fcmService;
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;

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

    @Transactional
    public void makeGroupNotification(String type, Club club) {
//        NotificationRequestDto notificationRequestDto = null;
//        String token = null;
        List<UserClub> userClubList = userClubRepository.findByClubIdAndState(club.getId(), UserClubState.ACCEPT);
        switch(type) {
            case "meeting_add":
                userClubList.forEach(member -> {
                    if (!member.getUser().getId().equals(club.getUser().getId())) {
                        String token = redisUtil.getValue(prefix + member.getUser().getId());
                        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                                .toUserFCMToken(token)
                                .title("새로운 모임이 생성됐어요 :)")
                                .message(club.getName() + " 클럽에 새로운 모임이 생성됐어요." + "\n" + "스케줄을 확인해 주세요 :)")
                                .build();
                        saveNotification(notificationRequestDto, member.getUser().getNickname());
                        if (token != null) {
                            sendNotification(notificationRequestDto);
                        }
                    }
                });
                break;
            case "meeting_cancel":
                userClubList.forEach(member -> {
                    if (!member.getUser().getId().equals(club.getUser().getId())) {
                        String token = redisUtil.getValue(prefix + member.getUser().getId());
                        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                                .toUserFCMToken(token)
                                .title("모임이 취소됐어요 :(")
                                .message(club.getName() + " 클럽에 예정됐던 모임이 취소됐어요." + "\n" + "스케줄을 확인해 주세요 :)")
                                .build();
                        saveNotification(notificationRequestDto, member.getUser().getNickname());
                        if (token != null) {
                            sendNotification(notificationRequestDto);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public void makeBadgeNotification(int badgeId, User user) {
        if (user.isExistBadge(badgeId)) return;

        NotificationRequestDto notificationRequestDto = null;
        String token = redisUtil.getValue(prefix + user.getId());
        user.updateBadgeStatus(badgeId);
        userRepository.save(user);

        notificationRequestDto = NotificationRequestDto.builder()
                .toUserFCMToken(token)
                .title("새로운 배지를 획득했어요 :)")
                .message("'" + badgeName[badgeId] + "' 배지를 새로 획득했어요." + "\n" + "마이페이지에서 확인해 보세요 :)")
                .build();

        saveNotification(notificationRequestDto, user.getNickname());
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
