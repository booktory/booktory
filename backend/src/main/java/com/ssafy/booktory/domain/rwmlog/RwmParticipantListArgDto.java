package com.ssafy.booktory.domain.rwmlog;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RwmParticipantListArgDto {
    Long userId;
    String userName;
    String profileImg;
    String bookName;

    public RwmParticipantListArgDto(RwmLog rwmLog){
        this.userId = rwmLog.getUser().getId();
        this.userName = rwmLog.getUser().getName();
        this.profileImg = rwmLog.getUser().getProfileImg();
        this.bookName = rwmLog.getBookName();
    }
}
