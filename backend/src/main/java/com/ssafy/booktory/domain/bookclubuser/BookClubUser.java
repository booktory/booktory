package com.ssafy.booktory.domain.bookclubuser;

import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookclub_id")
    private BookClub bookClub;

    @Builder
    public BookClubUser(Long id, User user, BookClub bookClub) {
        this.id = id;
        this.user = user;
        this.bookClub = bookClub;
    }
}
