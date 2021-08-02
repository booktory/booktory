package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.club.Club;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel("읽을 책 등록 요청 Dto")
public class BookClubCreateRequestDto {
    Long bookId;
    Long clubId;

    public BookClub toEntity(Book book, Club club) {
        return BookClub.builder()
                .club(club)
                .book(book)
                .build();
    }

    @Builder
    public BookClubCreateRequestDto(Long bookId, Long clubId) {
        this.bookId = bookId;
        this.clubId = clubId;
    }
}
