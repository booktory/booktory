package com.ssafy.booktory.domain.rwm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RwmListResponseDto {
    Long id;
    String name;
    String info;
    String img;
    int userCount;

    public RwmListResponseDto(Rwm rwm, int userCount){
        this.id = rwm.getId();
        this.name = rwm.getName();
        this.info = rwm.getInfo();
        this.img = rwm.getImg();
        this.userCount = userCount;
    }
}
