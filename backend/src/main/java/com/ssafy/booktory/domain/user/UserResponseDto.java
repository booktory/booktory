package com.ssafy.booktory.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private Long id;
    private String nickname;
    private String name;
    private LocalDate birth;
    private String profileImg;
    private String phone;
    private int mainBadge;
    private List<Integer> badgeList;

    public UserResponseDto(User user, List<Integer> badgeList) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.profileImg = user.getProfileImg();
        this.phone = user.getPhone();
        this.mainBadge = user.getMainBadge();
        this.badgeList = badgeList;
    }

}
