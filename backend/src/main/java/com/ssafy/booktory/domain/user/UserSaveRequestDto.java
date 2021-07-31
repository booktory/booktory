package com.ssafy.booktory.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDto {

    private String email;
    private String nickname;
    private String password;
    private String name;
    private LocalDate birth;
    private String profileImg;
    private String phone;

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .name(name)
                .birth(birth)
                .profileImg(profileImg)
                .phone(phone)
                .build();
    }

}