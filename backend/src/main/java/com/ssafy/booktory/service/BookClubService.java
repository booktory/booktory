package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.*;
import com.ssafy.booktory.domain.bookclubuser.BookClubParticipantDto;
import com.ssafy.booktory.domain.bookclubuser.BookClubUser;
import com.ssafy.booktory.domain.bookclubuser.BookClubUserRepository;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookClubService {

    private final BookClubRepository bookClubRepository;
    private final BookRepository bookRepository;
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final BookClubUserRepository bookClubUserRepository;
    private final NotificationService notificationService;

    public BookClub createBookToRead(BookClubCreateRequestDto bookClubCreateRequestDto){
        Book book = bookRepository.findById(bookClubCreateRequestDto.getBookId())
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 책 입니다."));
        Club club = clubRepository.findById(bookClubCreateRequestDto.getClubId())
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));

        BookClub bookClub = bookClubCreateRequestDto.toEntity(book, club);
        return bookClubRepository.save(bookClub);
    }

    @Transactional
    public BookClub addMeeting(BookClubAddRequestDto bookClubAddRequestDto) throws Exception{
        BookClub bookClub = bookClubRepository.findById(bookClubAddRequestDto.getId())
                .orElseThrow(()->new NoSuchElementException("읽을 책으로 등록되어있지 않습니다."));
//        if(!bookClub.getClub().getId().equals(bookClubAddRequestDto.getClubId())
//                || !bookClub.getBook().getId().equals(bookClubAddRequestDto.getBookId())){
//            throw new IllegalAccessException("클럽 또는 책 정보가 일치하지 않습니다.");
//        }
        bookClub.setMeetingTime(LocalDateTime.now(), bookClubAddRequestDto.getEndDateTime());
        notificationService.makeGroupNotification("meeting_add", bookClub.getClub());
        return bookClubRepository.save(bookClub);
    }

    @Transactional
    public BookClub cancelMeeting(Long bookClubId) {
        BookClub bookClub = bookClubRepository.findById(bookClubId)
                .orElseThrow(()->new NoSuchElementException("등록되지 않은 모임입니다."));
        bookClub.setMeetingTime( null, null);
        notificationService.makeGroupNotification("meeting_cancel", bookClub.getClub());
        return bookClubRepository.save(bookClub);
    }

    @Transactional
    public List<BookClubListResponseDto> findBookClubList(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("존재하지않는 클럽입니다."));
        List<BookClub> bookClubs = bookClubRepository.findByClub(club);
        List<BookClubListResponseDto> bookClubListResponseDtoList = new ArrayList<>();

        for(BookClub bookClub : bookClubs){
            List<BookClubParticipantDto> participants = new ArrayList<>();
            for(BookClubUser participant : bookClub.getBookClubUsers()){
                participants.add(new BookClubParticipantDto(participant.getUser()));
            }
            bookClubListResponseDtoList.add(new BookClubListResponseDto(bookClub, participants));
        }
        return bookClubListResponseDtoList;
    }

    @Transactional
    public void deleteBookClub(Long id) {
        BookClub bookClub = bookClubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("이미 삭제 처리된 요청입니다."));
        bookClubRepository.delete(bookClub);
    }

    public void attendToMeeting(Long id, Long userId) {
        BookClub bookClub = bookClubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 모임입니다. "));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("사용자 정보가 없습니다."));
        BookClubUser bookClubUser = BookClubUser.builder()
                .bookClub(bookClub)
                .user(user)
                .build();
        bookClubUserRepository.save(bookClubUser);

        int meetingCnt = bookClubUserRepository.countBookClubUserByUserId(user.getId());
        switch(meetingCnt) {
            case 1: notificationService.makeBadgeNotification(3, user); break;
            case 10: notificationService.makeBadgeNotification(4, user); break;
            case 30: notificationService.makeBadgeNotification(5, user); break;
            case 50: notificationService.makeBadgeNotification(6, user); break;
            case 100: notificationService.makeBadgeNotification(7, user); break;
            case 200: notificationService.makeBadgeNotification(8, user); break;
            default: break;
        }

        int meetingRecentWeekCnt = bookClubRepository.countBookClubFromWeekByUserId(user.getId());
        switch(meetingRecentWeekCnt) {
            case 3: notificationService.makeBadgeNotification(9, user); break;
            case 7: notificationService.makeBadgeNotification(10, user); break;
        }

        int meetingRecentDayCnt = bookClubRepository.countBookClubFromDayByUserId(user.getId());
        if (meetingRecentDayCnt == 2) notificationService.makeBadgeNotification(11, user);
    }

    @Transactional
    public BookClubInfoResponseDto bookClubInfo(Long id) {
        BookClub bookClub = bookClubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 북클럽입니다."));

        return new BookClubInfoResponseDto(bookClub);
    }
}
