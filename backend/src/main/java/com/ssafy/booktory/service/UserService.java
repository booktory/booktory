package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.userbook.UserBook;
import com.ssafy.booktory.domain.userbook.UserBookRepository;
import com.ssafy.booktory.domain.book.BookByUserResponseDto;
import com.ssafy.booktory.domain.user.UserResponseDto;
import com.ssafy.booktory.domain.user.UserSaveRequestDto;
import com.ssafy.booktory.domain.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserBookRepository userBookRepository;
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

    public Boolean checkUserByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        user.update(userUpdateRequestDto.getNickname(), userUpdateRequestDto.getName(), userUpdateRequestDto.getBirth(), userUpdateRequestDto.getProfile_img(), userUpdateRequestDto.getPhone());
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
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
        user.setMain_badge(badgeId);
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
