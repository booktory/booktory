package com.ssafy.booktory.domain.club;

import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubFindResponseDto {
    private String name;
    private LocalDateTime date;
    private String img;
    private String info;
    private int nowMember; //
    private int maxMember;
    private Boolean isOpen;
    private int volumeRule;
    private int weekRule;
    private String freeRule;
    private List<String> genres; //

    public ClubFindResponseDto(Club club, int nowMember){
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.nowMember = nowMember;
        this.maxMember = club.getMaxMember();
        this.isOpen = club.getIsOpen();
        this.volumeRule = club.getVolumeRule();
        this.weekRule = club.getWeekRule();
        this.freeRule = club.getFreeRule();
        for(ClubGenre clubGenre : club.getGenres())
            this.genres.add(clubGenre.getGenre().getName());
    }
}
