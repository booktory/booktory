package com.ssafy.booktory.domain.bookclubuser;

import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.user.User;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    private Boolean joining;

    @Builder
    public BookClubUser(Long id, User user, BookClub bookClub, Boolean joining) {
        this.id = id;
        this.user = user;
        this.bookClub = bookClub;
        this.joining = joining;
    }

    public void setJoiningStatus(Boolean joining) {
        this.joining = joining;
    }
}
