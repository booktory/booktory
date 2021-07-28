package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.dto.user.UserSaveRequestDto;
import com.ssafy.booktory.service.UserService;
import com.ssafy.booktory.util.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원 가입", notes = "필요한 정보를 받아 회원가입한다.")
    @PostMapping()
    public ResponseEntity<Void> register(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        User user = userService.register(userSaveRequestDto);
        return ResponseEntity.ok().build();
    }

}
