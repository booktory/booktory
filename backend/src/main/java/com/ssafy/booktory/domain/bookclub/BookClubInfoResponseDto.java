package com.ssafy.booktory.domain.bookclub;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookClubInfoResponseDto {
    private String clubName;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDateTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDateTime;

    public BookClubInfoResponseDto(BookClub bookClub){
        this.clubName = bookClub.getClub().getName();
        this.bookTitle = bookClub.getBook().getTitle();
        this.bookAuthor = bookClub.getBook().getAuthor();
        this.bookTranslator = bookClub.getBook().getTranslators();
        this.bookPublisher = bookClub.getBook().getPublisher();
        this.bookDate = bookClub.getBook().getDate();
        this.startDateTime = bookClub.getStartDatetime();
        this.endDateTime = bookClub.getEndDatetime();
    }

    @Builder
    public BookClubInfoResponseDto(String clubName, String bookTitle, String bookAuthor, String bookTranslator, String bookPublisher,
                                   LocalDate bookDate, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.clubName = clubName;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookTranslator = bookTranslator;
        this.bookPublisher = bookPublisher;
        this.bookDate = bookDate;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
