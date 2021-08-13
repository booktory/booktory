package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.user.*;
import com.ssafy.booktory.domain.book.BookByUserResponseDto;
import com.ssafy.booktory.service.NotificationService;
import com.ssafy.booktory.service.UserService;
import com.ssafy.booktory.util.JwtTokenProvider;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Api(value = "User API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/users", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원 가입", notes = "필요한 정보를 받아 회원가입한다.")
    @PostMapping()
    public ResponseEntity<String> register(@RequestBody @ApiParam(value = "회원가입 정보") UserSaveRequestDto userSaveRequestDto) {
        User user = userService.register(userSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @ApiOperation(value = "회원 가입 추가 정보", notes = "추가 정보(프로필 이미지, 이름, 생년월일)를 받아 회원가입한다.")
    @PatchMapping("/extra")
    public ResponseEntity<String> registerExtraInfo(@RequestBody @ApiParam(value = "회원가입 정보") UserPatchExtraRequestDto userPatchExtraRequestDto) {
        User user = userService.registerExtraInfo(userPatchExtraRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "일반 로그인", notes = "아이디와 비밀번호를 받아 로그인한다.")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @ApiParam(value = "로그인 정보(아이디, 비밀번호)") UserLoginRequestDto userLoginRequestDto) {
        User user = userService.findByEmail(userLoginRequestDto.getEmail());
        if (!user.getIsAccept()) {
            throw new IllegalArgumentException("인증되지 않은 회원입니다.");
        }
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.doLogin(user));
        }
        throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }

    @ApiOperation(value = "소셜 로그인", notes = "구글 및 카카오 아이디를 통해 로그인한다.")
    @PostMapping("/social")
    public ResponseEntity<UserSocialLoginResponseDto> login(@RequestBody @ApiParam(value = "소셜 로그인 정보(이메일, socialType") UserSocialLoginRequestDto userSocialLoginRequestDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.socialLogin(userSocialLoginRequestDto));
    }


    @ApiOperation(value = "회원가입을 위한 이메일 인증을 진행한다.")
    @GetMapping("/authentication/{token}")
    public RedirectView authenticateEmail(@PathVariable @ApiParam(value = "사용자 인증 토큰") String token) {
        RedirectView redirectView = new RedirectView();
        String userEmail = userService.updateAcceptState(token);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("email", userEmail);
        redirectView.setAttributesMap(attributes);
        redirectView.setUrl("http://localhost:8080/register/extrainfo");
        return redirectView;
    }

    @ApiOperation(value = "비밀번호 찾기", notes = "이메일을 입력받은 후 해당 이메일로 전송된 링크를 통해 비밀번호를 변경한다.")
    @PostMapping("/password/{email}")
    public ResponseEntity<Void> findPassword(@PathVariable @ApiParam(value = "사용자 이메일") String email) {
        userService.findPassword(email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "비밀번호 변경 페이지 이동", notes = "링크를 통해 비밀번호 변경 페이지로 이동시킨다.")
    @GetMapping("/password/reset/{token}")
    public RedirectView goResetPassword(@PathVariable @ApiParam(value = "사용자 인증 토큰") String token) {
        Long id = Long.valueOf(jwtTokenProvider.getUserId(token));
        User user = userService.findUserById(id);
        RedirectView redirectView = new RedirectView();
        Map<String, String> attributes = new HashMap<>();
        attributes.put("email", user.getEmail());
        redirectView.setAttributesMap(attributes);
        redirectView.setUrl("http://localhost:8080/password/update");
        return redirectView;
    }

    @ApiOperation(value = "새로운 비밀번호 입력", notes = "새로운 비밀번호로 변경한다.")
    @PatchMapping("/password/reset")
    public ResponseEntity<String> resetPassword(@RequestBody @ApiParam(value = "변경할 비밀번호") UserResetPwdRequestDto userResetPwdRequestDto) {
        userService.resetPassword(userResetPwdRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "회원 정보 확인", notes = "마이페이지에서 회원 정보를 받아온다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserInfo(@ApiIgnore final Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfo(userId));
    }

    @ApiOperation(value = "회원 정보 수정", notes = "수정할 정보를 입력 받아 회원 정보를 수정한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PatchMapping()
    public ResponseEntity<String> updateUserInfo(@ApiIgnore final Authentication authentication,
                                               @RequestBody @ApiParam(value = "회원 수정에 필요한 정보", required = true) UserUpdateRequestDto userUpdateRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();
        userService.updateUser(userId, userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "비밀번호 변경", notes = "현재 비밀번호와 바꿀 비밀번호를 입력 받아 비밀번호를 변경한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@ApiIgnore final Authentication authentication,
                                               @RequestBody @ApiParam(value = "비밀번호 변경에 필요한 정보", required = true) UserChangePwdRequestDto userChangePwdRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        if (passwordEncoder.matches(userChangePwdRequestDto.getCurrent_password(), user.getPassword())) {
            userService.changePassword(user.getId(), userChangePwdRequestDto.getNew_password());
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 비밀번호입니다.");
    }

    @ApiOperation(value = "회원 탈퇴", notes = "현재 비밀번호를 입력 받아서 회원 탈퇴를 진행한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@ApiIgnore final Authentication authentication,
                                           @RequestBody @ApiParam(value = "현재 비밀번호", required = true) String password) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        if (passwordEncoder.matches(password, user.getPassword())) {
            userService.deleteUser(user.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 비밀번호입니다.");
    }

    @ApiOperation(value = "대표배지 설정", notes = "대표배지 번호를 통해 대표배지로 등록한다.")
    @PatchMapping("/{id}/main-badge/{badgeId}")
    public ResponseEntity<String> registerMainBadge(@PathVariable @ApiParam(value = "유저 아이디", required = true) Long id,
                                                  @PathVariable @ApiParam(value = "배지 번호", required = true) int badgeId) {
        userService.registerMainBadge(id, badgeId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @ApiOperation(value = "내가 읽은 책 확인", notes = "내 서재에서 내가 읽은 책들을 조회한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/books")
    public ResponseEntity<List<BookByUserResponseDto>> getReadBooks(@ApiIgnore final Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(userService.getReadBooks(user.getId()));
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃할 때 redis에 저장된 fcm token을 지워준다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@ApiIgnore final Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        notificationService.deleteToken(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

}
