package com.ssafy.booktory.domain.rwmlog;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RwmParticipantListArgDto {
    private Long userId;
    private String userNickName;
    private String profileImg;
    private String bookName;

    public RwmParticipantListArgDto(RwmLog rwmLog){
        this.userId = rwmLog.getUser().getId();
        this.userNickName = rwmLog.getUser().getNickname();
        this.profileImg = rwmLog.getUser().getProfileImg();
        this.bookName = rwmLog.getBookName();
    }
}
