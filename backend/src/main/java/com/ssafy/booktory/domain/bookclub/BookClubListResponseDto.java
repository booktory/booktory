package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.bookclubuser.BookClubParticipantDto;
import com.ssafy.booktory.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookClubListResponseDto {
    Long id;
    Long bookId;
    String bookTitle;
    String bookThumbnail;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    List<BookClubParticipantDto> userList = new ArrayList<>();

    public BookClubListResponseDto(BookClub bookClub, List<BookClubParticipantDto> userList){
        this.id = bookClub.getId();
        this.bookId = bookClub.getBook().getId();
        this.bookTitle = bookClub.getBook().getTitle();
        this.bookThumbnail = bookClub.getBook().getThumbnail();
        this.startDateTime = bookClub.getStartDatetime();
        this.endDateTime = bookClub.getEndDatetime();
        this.userList = userList;
    }
}