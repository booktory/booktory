package com.ssafy.booktory.domain.club;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubFindResponseDto {
    private Long id;
    private Boolean isLeader = false;
    private String name;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime date;
    private String img;
    private String info;
    private String leaderName;
    private int nowMember;
    private int maxMember;
    private Boolean isOpen;
    private int volumeRule;
    private int weekRule;
    private String freeRule;
    private List<Long> genres = new ArrayList<>();
    private String title;
    private String author;
    private String publisher;
    private String thumbnail;
    private Long bookClubId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm")
    private LocalDateTime endDateTime;

    public ClubFindResponseDto(Club club, int nowMember, Long userId){
        this.id = club.getId();
        this.isLeader = (userId.equals(club.getUser().getId()));
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.leaderName = club.getUser().getNickname();
        this.nowMember = nowMember;
        this.maxMember = club.getMaxMember();
        this.isOpen = club.getIsOpen();
        this.volumeRule = club.getVolumeRule();
        this.weekRule = club.getWeekRule();
        this.freeRule = club.getFreeRule();
        for(ClubGenre clubGenre : club.getGenres())
            this.genres.add(clubGenre.getGenre().getId());
    }

    public ClubFindResponseDto(Club club, int nowMember, Long userId, Book book, LocalDateTime endDateTime, Long bookClubId){
        this.id = club.getId();
        this.isLeader = (userId.equals(club.getUser().getId()));
        this.name = club.getName();
        this.date = club.getCreatedDate();
        this.img = club.getImg();
        this.info = club.getInfo();
        this.leaderName = club.getUser().getNickname();
        this.nowMember = nowMember;
        this.maxMember = club.getMaxMember();
        this.isOpen = club.getIsOpen();
        this.volumeRule = club.getVolumeRule();
        this.weekRule = club.getWeekRule();
        this.freeRule = club.getFreeRule();
        for(ClubGenre clubGenre : club.getGenres())
            this.genres.add(clubGenre.getGenre().getId());
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.thumbnail = book.getThumbnail();
        this.endDateTime = endDateTime;
        this.bookClubId = bookClubId;
    }
}
