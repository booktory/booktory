package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.bookclub.BookClubRepository;
import com.ssafy.booktory.domain.club.*;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.clubgenre.ClubGenreRepository;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.genre.Genre;
import com.ssafy.booktory.domain.genre.GenreRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.ssafy.booktory.domain.userclub.UserClubListResponseDto;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service("clubService")
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookClubRepository bookClubRepository;
    private final ClubGenreRepository clubGenreRepository;
    private final UserClubRepository userClubRepository;
    private final NotificationService notificationService;

    public void createClub(Long userId, ClubSaveRequestDto clubSaveRequestDto){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다."));
        if (clubRepository.existsByName(clubSaveRequestDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 클럽명입니다.");
        }

        if (userClubRepository.findAllByUserAndState(user, UserClubState.ACCEPT).size() == 3) {
            throw new IllegalArgumentException("클럽은 3개까지 가입 가능합니다.");
        }

        Club club = clubSaveRequestDto.toEntity(user);
        Club savedClub = clubRepository.save(club);

        List<BookClub> bookClubs = bookIdListToBookClubList(clubSaveRequestDto.getBooks(), savedClub);
        savedClub.updateBookClubs(bookClubs);
        bookClubs.forEach(bookClubRepository::save);

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubSaveRequestDto.getGenres(), savedClub);
        savedClub.updateGenres(clubGenres);
        clubGenres.forEach(clubGenreRepository::save);

        UserClub userClub = UserClub.builder()
                .club(savedClub)
                .user(user)
                .state(UserClubState.ACCEPT)
                .build();
        userClubRepository.save(userClub);

        int clubCnt = clubRepository.countClubByUserId(user.getId());
        if (clubCnt == 1) {
            notificationService.makeBadgeNotification(1, user);
        }
        if (clubCnt == 3) {
            notificationService.makeBadgeNotification(2, user);
        }
    }

    @Transactional
    public ClubFindResponseDto findClub(Long id, Long userId){
        Club club = clubRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));
        int nowMember = getClubMembersCount(club);

        Long bookClubId = null;
        Book book = null;
        LocalDateTime endDateTime = null;

        BookClub bookClub = bookClubRepository.findByClubIdFirstByOrderByEndDatetimeDesc(id);

        if (bookClub != null && ChronoUnit.MINUTES.between(bookClub.getEndDatetime(), LocalDateTime.now()) <= 60) {
            book = bookRepository.findById(bookClub.getBook().getId()).orElseThrow(() -> new NoSuchElementException("존재하는 책이 없습니다."));
            endDateTime = bookClub.getEndDatetime();
            bookClubId = bookClub.getId();
        }

        if (book == null) return new ClubFindResponseDto(club, nowMember, userId);
        else return new ClubFindResponseDto(club, nowMember, userId, book, endDateTime, bookClubId);
    }

    @Transactional
    public List<ClubListFindResponseDto> findJoinedClubList(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 회원입니다."));
        List<UserClub> userClubs = userClubRepository.findAllByUserAndState(user, UserClubState.ACCEPT);

        List<ClubListFindResponseDto> clubListFindResponseDtoList = new ArrayList<>();
        for(UserClub userClub : userClubs){
            clubListFindResponseDtoList.add(new ClubListFindResponseDto(userClub.getClub()));
        }
        return clubListFindResponseDtoList;
    }

    public Club updateClub(Long id, ClubUpdateRequestDto clubUpdateRequestDto, Long leaderId) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        User leader = userRepository.findById(leaderId)
                .orElseThrow(()-> new NoSuchElementException("사용자 정보가 존재하지 않습니다."));
        if(!club.getUser().getId().equals(leader.getId()))
            throw new IllegalStateException("클럽장만 클럽 정보를 수정할 수 있습니다.");

        if(getClubMembersCount(club) > clubUpdateRequestDto.getMaxMember())
            throw new IllegalArgumentException("최대 수용멤버를 현재 멤버 수 보다 적게 설정할 수 없습니다.");

        club.updateClub(clubUpdateRequestDto.getName(), clubUpdateRequestDto.getImg(), clubUpdateRequestDto.getInfo(),
                clubUpdateRequestDto.getMaxMember(), clubUpdateRequestDto.getIsOpen(),
                clubUpdateRequestDto.getVolumeRule(), clubUpdateRequestDto.getWeekRule(), clubUpdateRequestDto.getFreeRule());

        List<ClubGenre> deleteClubGenres = clubGenreRepository.findByClubId(club.getId());
        clubGenreRepository.deleteAll(deleteClubGenres);

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubUpdateRequestDto.getGenres(), club);

        club.updateGenres(clubGenres);

        return clubRepository.save(club);
    }

    @Transactional
    public UserClub applyToClub(Long userId, Long clubId){
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 회원입니다."));
        if(userClubRepository.countByUserAndClub(user, club) != 0){
            throw new IllegalStateException("이미 가입 신청한 클럽입니다.");
        }
        if (userClubRepository.findAllByUserAndState(user, UserClubState.ACCEPT).size() == 3) {
            throw new IllegalArgumentException("클럽은 3개까지 가입 가능합니다.");
        }
        
        UserClub userClub = UserClub.builder()
                .user(user)
                .club(club)
                .state(UserClubState.APPLY)
                .build();

        notificationService.makeNotification("apply", club, user);

        return userClubRepository.save(userClub);
    }

    @Transactional
    public Optional<UserClub> acceptToClub(Long leaderId, Long clubId, Long userClubId, boolean isAccept){
        UserClub userClub = userClubRepository.findById(userClubId)
                .orElseThrow(()->new NoSuchElementException("존재하지않는 가입신청입니다."));

        if(!clubId.equals(userClub.getClub().getId()))
            throw new IllegalStateException("클럽에 접근권한이 없습니다.");
        if(!leaderId.equals(userClub.getClub().getUser().getId()))
            throw new IllegalStateException("클럽장만 가입을 승인할 수 있습니다.");
        if(userClub.getState() == UserClubState.ACCEPT)
            throw new IllegalStateException("이미 가입 수락된 클럽입니다.");
        if(!isAccept) {
            userClubRepository.delete(userClub);
            notificationService.makeNotification("reject", userClub.getClub(), userClub.getUser());
            return Optional.empty();
        }
        if(getClubMembersCount(userClub.getClub()) >= userClub.getClub().getMaxMember())
            throw new IllegalArgumentException("멤버를 더이상 수용할 수 없습니다.");
        userClub.acceptJoin();
        notificationService.makeNotification("accept", userClub.getClub(), userClub.getUser());
        notificationService.makeBadgeNotification(0, userClub.getUser());

        return Optional.of(userClubRepository.save(userClub));
    }
    public void deleteJoin(Long userId, Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 회원입니다."));
        UserClub userClub = userClubRepository.findByUserAndClub(user, club);
        userClubRepository.delete(userClub);
    }

    public void deleteClub(Long leaderId, Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));

        if(!club.getUser().getId().equals(leaderId)){
            throw new IllegalStateException("클럽 삭제 권한이 없습니다.");
        }
        clubRepository.delete(club);
    }

    @Transactional
    public List<UserClubListResponseDto> appliedUserList(Long clubId, Long leaderId){
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));

        if(!leaderId.equals(club.getUser().getId())){
            throw new IllegalStateException("클럽장만 신청자를 열람할 수 있습니다.");
        }

        List<UserClubListResponseDto> userClubListResponseDtos = new ArrayList<>();
        List<UserClub> userClubs = userClubRepository.findByClubIdAndState(clubId, UserClubState.APPLY);
        for(UserClub userClub : userClubs)
            userClubListResponseDtos.add(new UserClubListResponseDto(userClub));

        return userClubListResponseDtos;
    }

    @Transactional
    public List<UserClubListResponseDto> acceptedUserList(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));

        List<UserClubListResponseDto> userClubListResponseDtos = new ArrayList<>();
        List<UserClub> userClubs = userClubRepository.findByClubIdAndState(clubId, UserClubState.ACCEPT);
        for(UserClub userClub : userClubs)
            userClubListResponseDtos.add(new UserClubListResponseDto(userClub));

        return userClubListResponseDtos;
    }

    private List<BookClub> bookIdListToBookClubList(List<Long> bookIdList, Club savedClub){
        List<BookClub> bookClubs = new ArrayList<>();
        for(Long bookId : bookIdList){
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(()->new IllegalArgumentException("존재하지않는 책입니다."));
            BookClub bookClub = BookClub.builder()
                    .book(book)
                    .club(savedClub)
                    .build();
            bookClubs.add(bookClub);

        }
        return bookClubs;
    }
    private List<ClubGenre> genreIdListToClubGenreList(List<Long> genreIdList, Club savedClub){
        List<ClubGenre> clubGenres = new ArrayList<>();
        for(Long genreId : genreIdList){
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(()-> new IllegalArgumentException("존재하지않는 장르입니다."));
            ClubGenre clubGenre = ClubGenre.builder()
                    .genre(genre)
                    .club(savedClub)
                    .build();
            clubGenres.add(clubGenre);
        }
        return clubGenres;
    }
    private int getClubMembersCount(Club club){
        return userClubRepository.findAllByClubAndState(club, UserClubState.ACCEPT).size();
    }

    public Boolean isSelectedBook(Long id, Long bookId) {
        return bookClubRepository.existsByClubIdAndBookId(id, bookId);
    }
}
