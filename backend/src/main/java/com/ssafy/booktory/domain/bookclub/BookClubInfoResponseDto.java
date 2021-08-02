package com.ssafy.booktory.domain.bookclub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookClubInfoResponseDto {
    String clubName;
    String bookTitle;
    String bookAuthor;
    String bookTranslator;
    String bookPublisher;
    LocalDate bookDate;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

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
