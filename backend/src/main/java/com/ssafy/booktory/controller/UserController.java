package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.dto.user.UserLoginRequestDto;
import com.ssafy.booktory.dto.user.UserSaveRequestDto;
import com.ssafy.booktory.dto.user.UserUpdateRequestDto;
import com.ssafy.booktory.service.UserService;
import com.ssafy.booktory.util.JwtTokenProvider;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "회원 가입", notes = "필요한 정보를 받아 회원가입한다.")
    @PostMapping()
    public ResponseEntity<Void> register(@RequestBody @ApiParam(value = "회원가입 정보") UserSaveRequestDto userSaveRequestDto) {
        User user = userService.register(userSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일반 로그인", notes = "아이디와 비밀번호를 받아 로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @ApiParam(value = "로그인 정보(아이디, 비밀번호)") UserLoginRequestDto userLoginRequestDto) {
        User user = userService.findByEmail(userLoginRequestDto.getEmail());
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(jwtTokenProvider.createToken(user.getId(), user.getRoles()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 비밀번호입니다.");
    }

    @ApiOperation(value = "회원가입 혹은 로그인 진행 시 이메일 중복확인", notes = "이미 가입된 이메일인지 중복 확인한다.")
    @GetMapping("/email/{email}")
    public ResponseEntity<String> checkDuplicateEmail(@PathVariable @ApiParam(value = "입력한 이메일", required = true) String email) {
        Boolean isExistUser = userService.checkUserByEmail(email);
        if (isExistUser) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("loginUser");
        }
        return ResponseEntity.status(HttpStatus.OK).body("registerUser");
    }

    @ApiOperation(value = "닉네임 생성 시 닉네임 중복확인", notes = "이미 등록된 닉네임인지 중복 확인한다.")
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<String> checkDuplicateNickname(@PathVariable @ApiParam(value = "입력한 닉네임", required = true) String nickname) {
        Boolean isExistNickname = userService.checkUserByNickname(nickname);
        if (isExistNickname) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("fail");
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "회원 정보 수정", notes = "수정할 정보를 입력 받아 회원 정보를 수정한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PatchMapping()
    public ResponseEntity<Void> updateUserInfo(@ApiIgnore final Authentication authentication,
                                               @RequestBody @ApiParam(value = "회원 수정에 필요한 정보", required = true) UserUpdateRequestDto userUpdateRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();
        System.out.println(userId);
        User user = userService.updateUser(userId, userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "회원 탈퇴", notes = "현재 비밀번호를 입력 받아서 회원 탈퇴를 진행한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@ApiIgnore final Authentication authentication,
                                           @RequestBody @ApiParam(value = "현재 비밀번호") String password) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        if (passwordEncoder.matches(password, user.getPassword())) {
            userService.deleteUser(user.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
