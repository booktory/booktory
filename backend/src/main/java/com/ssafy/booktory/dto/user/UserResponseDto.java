package com.ssafy.booktory.dto.user;

import com.ssafy.booktory.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    private String profile_img;
    private String phone;
    private int main_badge;
    private List<Integer> badge_list;

    public UserResponseDto(User user, List<Integer> badge_list) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.profile_img = user.getProfile_img();
        this.phone = user.getPhone();
        this.main_badge = user.getMain_badge();
        this.badge_list = badge_list;
    }

}
