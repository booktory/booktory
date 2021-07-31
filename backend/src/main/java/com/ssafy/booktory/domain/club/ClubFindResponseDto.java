package com.ssafy.booktory.domain.club;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubFindResponseDto {
    String name;
    LocalDateTime date;
    String img;
    String info;
    int maxMember;
    Boolean isOpen;
    int volumRule;
    int weekRule;
    String freeRule;

    public ClubFindResponseDto(Club club){
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.maxMember = club.getMax_member();
        this.isOpen = club.getIs_open();
        this.volumRule = club.getVolume_rule();
        this.weekRule = club.getWeek_rule();
        this.freeRule = club.getFree_rule();
    }
}
