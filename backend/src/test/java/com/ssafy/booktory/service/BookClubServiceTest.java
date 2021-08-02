package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.bookclub.BookClubAddRequestDto;
import com.ssafy.booktory.domain.bookclub.BookClubCreateRequestDto;
import com.ssafy.booktory.domain.bookclub.BookClubRepository;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BookClubServiceTest {

    @Autowired BookClubService bookClubService;
    @Autowired BookClubRepository bookClubRepository;
    @Autowired BookRepository bookRepository;
    @Autowired ClubRepository clubRepository;
    @Autowired UserRepository userRepository;
//    @Autowired EntityManager em;

    private Book book;
    private User user;
    private Club club;
    private BookClub bookClub;

    @BeforeEach
    private void setUp(){
        Book book = Book.builder()
                .isbn("223947928379")
                .title("아무제목의 책")
                .author("아무작가")
                .price(10000)
                .build();
        this.book = bookRepository.save(book);

        User user = User.builder()
                .nickname("북클럽테스트리더")
                .email("bookclub@test.com")
                .password("1234")
                .build();
        this.user = userRepository.save(user);

        Club club = Club.builder()
                .name("bookclubtest")
                .user(this.user)
                .maxMember(7)
                .info("북클럽테스트용 클럽입니다.")
                .volumeRule(1)
                .weekRule(3)
                .isOpen(true)
                .build();
        this.club = clubRepository.save(club);
    }

    @Test
    @Rollback(value = true)
    public void 읽을책_등록(){
        //given
        BookClubCreateRequestDto bookClubCreateRequestDto = BookClubCreateRequestDto.builder()
                .bookId(book.getId())
                .clubId(club.getId())
                .build();

        //when
        BookClub createdBookClub = bookClubService.createBookToRead(bookClubCreateRequestDto);

        //then
        BookClub saved = bookClubRepository.findByBookAndClub(book, club)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 (읽을)책입니다."));
        assertEquals(createdBookClub.getId(), saved.getId());
    }

    @Test
    @Rollback(value = true)
    public void 모임_등록() throws Exception {
        //given
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2021, 12, 31, 12, 00);

        BookClubCreateRequestDto bookClubCreateRequestDto = BookClubCreateRequestDto.builder()
                .bookId(book.getId())
                .clubId(club.getId())
                .build();
        BookClub createdBookClub = bookClubService.createBookToRead(bookClubCreateRequestDto);

        BookClubAddRequestDto bookClubAddRequestDto = BookClubAddRequestDto.builder()
                .id(createdBookClub.getId())
                .bookId(book.getId())
                .clubId(club.getId())
                .startDateTime(start)
                .endDateTime(end)
                .build();

        //when
        BookClub testEntity = bookClubService.addMeeting(bookClubAddRequestDto);

        //then
        BookClub savedEntity = bookClubRepository.findById(createdBookClub.getId())
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 북클럽입니다."));
        assertEquals(testEntity.getStartDatetime(), savedEntity.getStartDatetime());
    }

    @Test
    @Rollback(value = true)
    public void 모임_취소() throws Exception{
        //given
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2021, 12, 31, 12, 00);

        BookClub bookClub = BookClub.builder()
                .book(book)
                .club(club)
                .startDatetime(start)
                .endDatetime(end)
                .build();
        bookClub = bookClubRepository.save(bookClub);

        //when
        BookClub canceled = bookClubService.cancelMeeting(bookClub.getId());

        //then
        assertEquals(null, canceled.getStartDatetime());
        assertEquals(null, canceled.getEndDatetime());
    }
}