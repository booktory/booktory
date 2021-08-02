package com.ssafy.booktory.domain.book;

import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.userbook.UserBook;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotNull
    private String title;

    @Column(length = 20)
    @NotNull
    private String isbn;

    private String info;

    @Column(length = 30)
    private String author;

    @Column(length = 30)
    private String translators;

    @Column(length = 50)
    private String publisher;

    private int price;

    private LocalDate date;

    private String thumbnail;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookClub> bookClubs = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<UserBook> userBooks = new ArrayList<>();

    @Builder
    public Book(String title, String isbn, String info, String author, String translators,
                String publisher, int price, LocalDate date, String thumbnail) {
        this.title = title;
        this.isbn = isbn;
        this.info = info;
        this.author = author;
        this.translators = translators;
        this.publisher = publisher;
        this.price = price;
        this.date = date;
        this.thumbnail = thumbnail;
    }
}