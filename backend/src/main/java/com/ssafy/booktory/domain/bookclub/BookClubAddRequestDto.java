package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.club.Club;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Api("미팅 시간 추가 DTO")
@Getter
@NoArgsConstructor
public class BookClubAddRequestDto{
    private Long id;
    private Long bookId;
    private Long clubId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Builder
    public BookClubAddRequestDto(Long bookId, Long clubId, Long id, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.bookId = bookId;
        this.clubId = clubId;
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
