package com.ssafy.booktory.domain.userclub;

import com.ssafy.booktory.domain.common.UserClubState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserClubListResponseDto {
    Long id;
    String userNickname;
    String userProfileImg;

    public UserClubListResponseDto(UserClub userClub){
        this.id = userClub.getId();
        this.userNickname = userClub.getUser().getNickname();
        this.userProfileImg = userClub.getUser().getProfileImg();
    }
}
