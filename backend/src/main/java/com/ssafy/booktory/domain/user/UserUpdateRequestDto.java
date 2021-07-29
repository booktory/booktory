package com.ssafy.booktory.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    private String nickname;
    private String name;
    private LocalDate birth;
    private String profileImg;
    private String phone;

}
