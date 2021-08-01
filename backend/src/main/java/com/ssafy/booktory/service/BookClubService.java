package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.bookclub.BookClubAddRequestDto;
import com.ssafy.booktory.domain.bookclub.BookClubRepository;
import com.ssafy.booktory.domain.bookclub.BookClubCreateRequestDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookClubService {

    private final BookClubRepository bookClubRepository;
    private final BookRepository bookRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;


    public BookClub createBookToRead(BookClubCreateRequestDto bookClubCreateRequestDto){
        Book book = bookRepository.findById(bookClubCreateRequestDto.getBookId())
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 책 입니다."));
        Club club = clubRepository.findById(bookClubCreateRequestDto.getClubId())
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));

        BookClub bookClub = bookClubCreateRequestDto.toEntity(book, club);
        return bookClubRepository.save(bookClub);
    }

    public BookClub addMeeting(BookClubAddRequestDto bookClubAddRequestDto) throws Exception{
        BookClub bookClub = bookClubRepository.findById(bookClubAddRequestDto.getId())
                .orElseThrow(()->new NoSuchElementException("읽을 책으로 등록되어있지 않습니다."));
        if( bookClub.getClub().getId() != bookClubAddRequestDto.getClubId()
                || bookClub.getBook().getId() != bookClubAddRequestDto.getBookId()){
            throw new IllegalAccessException("클럽 또는 책 정보가 일치하지 않습니다.");
        }
        bookClub.setMeetingTime(bookClubAddRequestDto);
        return bookClubRepository.save(bookClub);
    }
}
