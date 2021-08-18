package com.ssafy.booktory.domain.userbook;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.common.BaseTimeEntity;
import com.ssafy.booktory.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String comment;

    @Builder
    public UserBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
