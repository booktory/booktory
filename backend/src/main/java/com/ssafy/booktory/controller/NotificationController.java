package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.notification.FCMRequestDto;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserSaveRequestDto;
import com.ssafy.booktory.service.NotificationService;
import com.ssafy.booktory.service.UserService;
import com.ssafy.booktory.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Notification API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    static final String prefix = "FCM_TOKEN_";
    private final RedisUtil redisUtil;
    private final UserService userService;
    private final NotificationService notificationService;

    @ApiOperation(value = "FCM 토큰 등록", notes = "로그인할 때 FCM Token 정보를 받아 redis에 저장한다")
    @PostMapping()
    public ResponseEntity<String> register(@RequestBody @ApiParam(value = "발급 받은 FCM Token") FCMRequestDto fcmRequestDto) {
        User user = userService.findByEmail(fcmRequestDto.getEmail());
        if (redisUtil.getValue(prefix + user.getId()) == null) {
            notificationService.registerToken(user.getId(), fcmRequestDto.getToken());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

}