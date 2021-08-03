package com.ssafy.booktory.domain.club;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubFindResponseDto {
    private String name;
    private LocalDateTime date;
    private String img;
    private String info;
    private int maxMember;
    private Boolean isOpen;
    private int volumeRule;
    private int weekRule;
    private String freeRule;

    public ClubFindResponseDto(Club club){
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.maxMember = club.getMaxMember();
        this.isOpen = club.getIsOpen();
        this.volumeRule = club.getVolumeRule();
        this.weekRule = club.getWeekRule();
        this.freeRule = club.getFreeRule();
    }
}
