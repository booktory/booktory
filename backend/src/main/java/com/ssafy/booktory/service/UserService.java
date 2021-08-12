package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.user.*;
import com.ssafy.booktory.domain.userbook.UserBook;
import com.ssafy.booktory.domain.userbook.UserBookRepository;
import com.ssafy.booktory.domain.book.BookByUserResponseDto;
import com.ssafy.booktory.util.JwtTokenProvider;
import com.ssafy.booktory.util.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserBookRepository userBookRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final Uploader uploader;

    @Value("${spring.mail.username}")
    private static String FROM_ADDRESS;

    public User register(UserSaveRequestDto userSaveRequestDto) {
        if (userRepository.existsByNickname(userSaveRequestDto.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        if (userRepository.existsByEmail(userSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = userSaveRequestDto.toEntity();
        user.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        user = userRepository.save(user);

        String token = jwtTokenProvider.createToken(user.getId(), user.getRoles());
        sendMail(userSaveRequestDto.getEmail(), token, 1);
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));
    }

    public String updateAcceptState(String token) {
        Long id = Long.valueOf(jwtTokenProvider.getUserId(token));
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디입니다."));
        user.updateAcceptState(true);
        userRepository.save(user);
        return user.getEmail();
    }

    public User registerExtraInfo(UserPatchExtraRequestDto userPatchExtraRequestDto) {
        User user = userRepository.findByEmail(userPatchExtraRequestDto.getEmail()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));
        user.addExtraInfo(
                userPatchExtraRequestDto.getName(),
                userPatchExtraRequestDto.getBirth(),
                userPatchExtraRequestDto.getProfileImg(),
                userPatchExtraRequestDto.getPhone()
        );
        return userRepository.save(user);
    }

    public UserLoginResponseDto doLogin(User user) {
        String jwt = jwtTokenProvider.createToken(user.getId(), user.getRoles());
        return new UserLoginResponseDto(jwt, user.getId(), user.getNickname(), user.getEmail());
    }

    public void findPassword(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new NoSuchElementException("가입되지 않은 이메일입니다.");
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));
        String token = jwtTokenProvider.createToken(user.getId(), user.getRoles());
        sendMail(email, token, 2);
    }

    public void resetPassword(UserResetPwdRequestDto userResetPwdRequestDto) {
        User user = userRepository.findByEmail(userResetPwdRequestDto.getEmail()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));
        user.setPassword(passwordEncoder.encode(userResetPwdRequestDto.getPassword()));
        userRepository.save(user);
    }

    private void sendMail(String email, String token, int type) {
        SimpleMailMessage message = new SimpleMailMessage();
        StringBuilder text = new StringBuilder();
        message.setTo(email);
        message.setFrom(FROM_ADDRESS);
        if (type == 1) {
            message.setSubject("책토리 회원가입 인증 이메일입니다.");
            URL url = null;
            try {
                url = new URL("http://localhost:8080/api/users/authentication/" + token);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            text = new StringBuilder();
            text.append("다음 링크로 접속하여 인증 해주세요!").append('\n').append(url);
        } else {
            message.setSubject("책토리 비밀번호 변경 이메일입니다.");
            URL url = null;
            try {
                url = new URL("http://localhost:8080/api/users/password/reset/" + token);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            text = new StringBuilder();
            text.append("다음 링크로 접속하여 새로운 비밀번호를 입력 해주세요!").append('\n').append(url);
        }

        message.setText(text.toString());
        mailSender.send(message);
    }

    public void updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        String originFileURL = user.getProfileImg();
        if(!originFileURL.equals(userUpdateRequestDto.getProfileImg()))
            uploader.deleteS3Instance(originFileURL);
        user.update(userUpdateRequestDto.getNickname(), userUpdateRequestDto.getName(), userUpdateRequestDto.getBirth(), userUpdateRequestDto.getProfileImg(), userUpdateRequestDto.getPhone());
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("존재하지 않는 회원입니다."));
        String fileURL = user.getProfileImg();
        uploader.deleteS3Instance(fileURL);
        userRepository.deleteById(userId);
    }

    public UserResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        List<Integer> badges = user.getBadgeList(user.getBadge());
        return new UserResponseDto(user, badges);
    }

    public void changePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void registerMainBadge(Long id, int badgeId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        user.setMainBadge(badgeId);
        userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public List<BookByUserResponseDto> getReadBooks(Long userId) {
        List<UserBook> books = userBookRepository.findByUserId(userId);
        return books.stream()
                .map(book -> new BookByUserResponseDto(book.getBook().getId(), book.getBook().getTitle(), book.getBook().getThumbnail()))
                .collect(Collectors.toList());
    }

}
