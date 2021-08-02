package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.bookclubuser.BookClubUser;
import com.ssafy.booktory.domain.club.Club;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;

    //모임 참석자
    @OneToMany(mappedBy = "bookClub", cascade = CascadeType.ALL)
    private List<BookClubUser> bookClubUsers = new ArrayList<>();

    @Builder
    public BookClub(Book book, Club club, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.book = book;
        this.club = club;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }
}
