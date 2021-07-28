package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserSaveRequestDto userSaveRequestDto) {
        User user = userSaveRequestDto.toEntity();
        user.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));
    }

    public Boolean checkUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
