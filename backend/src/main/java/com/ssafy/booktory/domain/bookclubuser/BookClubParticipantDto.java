package com.ssafy.booktory.domain.bookclubuser;

import com.ssafy.booktory.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookClubParticipantDto {
    Long userId;
    String userNickname;
    String profileImg;

    public BookClubParticipantDto(User user){
        this.userId = user.getId();
        this.userNickname = user.getNickname();
        this.profileImg = user.getProfileImg();
    }
}
