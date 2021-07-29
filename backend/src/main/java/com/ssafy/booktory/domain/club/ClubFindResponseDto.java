package com.ssafy.booktory.domain.club;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubFindResponseDto {
    String name;
    LocalDateTime date;
    String img;
    String info;
    int max_member;
    Boolean is_open;
    int volum_rule;
    int week_rule;
    String free_rule;

    public ClubFindResponseDto(Club club){
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.max_member = club.getMax_member();
        this.is_open = club.getIs_open();
        this.volum_rule = club.getVolume_rule();
        this.week_rule = club.getWeek_rule();
        this.free_rule = club.getFree_rule();
    }
}
