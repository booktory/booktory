package com.ssafy.booktory.domain.bookclub;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ssafy.booktory.domain.bookclubuser.BookClubParticipantDto;
import com.ssafy.booktory.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookClubListResponseDto {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private String bookThumbnail;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startDateTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endDateTime;
    private List<BookClubParticipantDto> userList = new ArrayList<>();

    public BookClubListResponseDto(BookClub bookClub, List<BookClubParticipantDto> userList){
        this.id = bookClub.getId();
        this.bookId = bookClub.getBook().getId();
        this.bookTitle = bookClub.getBook().getTitle();
        this.bookThumbnail = bookClub.getBook().getThumbnail();
        this.bookAuthor = bookClub.getBook().getAuthor();
        this.bookTranslator = bookClub.getBook().getTranslators();
        this.bookPublisher = bookClub.getBook().getPublisher();
        this.bookDate = bookClub.getBook().getDate();
        this.startDateTime = bookClub.getStartDatetime();
        this.endDateTime = bookClub.getEndDatetime();
        this.userList = userList;
    }
}