package com.ssafy.booktory.dto.user;

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
    private String profile_img;
    private String phone;

}
