package com.ssafy.booktory.domain.rwm;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RwmRoomResponseDto {
    String name;
    String info;
    String img;
    String apiLink;
    int userCount;

    public RwmRoomResponseDto(Rwm rwm, int userCount) {
        this.name = rwm.getName();
        this.info = rwm.getInfo();
        this.img = rwm.getImg();
        this.apiLink = rwm.getApiLink();
        this.userCount = userCount;
    }
}
