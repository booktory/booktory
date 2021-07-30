package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.club.*;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.genre.Genre;
import com.ssafy.booktory.domain.genre.GenreRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("clubService")
@RequiredArgsConstructor
public class ClubService {

    public static final Logger logger = LoggerFactory.getLogger(ClubService.class);

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final UserClubRepository userClubRepository;

    public Club createClub(ClubSaveRequestDto clubSaveRequestDto){

        User user = userRepository.findById(clubSaveRequestDto.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Club club = clubSaveRequestDto.toEntity(user);
        Club savedClub = clubRepository.save(club);

        List<BookClub> bookClubs = bookIdListToBookClubList(clubSaveRequestDto.getBooks(), savedClub);
        savedClub.updateBookClubs(bookClubs);

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubSaveRequestDto.getGenres(), savedClub);
        savedClub.updateGenres(clubGenres);

        UserClub userClub = UserClub.builder()
                .club(savedClub)
                .user(user)
                .state(UserClubState.ACCEPT)
                .build();
        userClubRepository.save(userClub);

        return clubRepository.save(savedClub);
    }

    public Club findClub(Long id){
        return clubRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));
    }

    @Transactional
    public ClubListFindResponseDto findJoinedClubList(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 회원입니다."));
        List<UserClub> userClubs = userClubRepository.findAllByUserAndState(user, UserClubState.ACCEPT);

        List<Club> clubs = new ArrayList<>();
        for(UserClub userClub : userClubs){
            clubs.add(userClub.getClub());
        }
        ClubListFindResponseDto clubListFindResponseDto = new ClubListFindResponseDto();
        clubListFindResponseDto.toDto(clubs);

        return clubListFindResponseDto;
    }

    public Club updateClub(Long id, ClubUpdateRequestDto clubUpdateRequestDto){
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));

        club.updateClub(clubUpdateRequestDto.getName(), clubUpdateRequestDto.getImg(), clubUpdateRequestDto.getInfo(),
                clubUpdateRequestDto.getMaxMember(), clubUpdateRequestDto.getIsOpen(),
                clubUpdateRequestDto.getVolumRule(), clubUpdateRequestDto.getWeekRule(), clubUpdateRequestDto.getFreeRule());

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubUpdateRequestDto.getGenres(), club);
        club.updateGenres(clubGenres);

        return clubRepository.save(club);
    }
/*
    public void addBooks(Long id, List<Long> books) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        List<BookClub> bookClubs = bookIdListToBookClubList(books, club);
        club.updateBookClubs(bookClubs);
        clubRepository.save(club);
    }
*/

    public UserClub applyToClub(Long userId, Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 회원입니다."));
        UserClub userClub = UserClub.builder()
                .user(user)
                .club(club)
                .state(UserClubState.APPLY)
                .build();
        return userClubRepository.save(userClub);
    }

    @Transactional
    public UserClub acceptToClub(Long leaderId, Long clubId, Long userClubId) throws Exception{
        UserClub userClub = userClubRepository.findById(userClubId)
                .orElseThrow(()->new NoSuchElementException("존재하지않는 가입신청입니다."));

        if(clubId != userClub.getClub().getId() ){
            throw new IllegalAccessException("클럽에 접근권한이 없습니다.");
        }
        if(leaderId != userClub.getClub().getUser().getId()){
            throw new IllegalAccessException("클럽장만 가입을 승인할 수 있습니다.");
        }
        if(userClub.getState() == UserClubState.ACCEPT){
            throw new IllegalStateException("이미 처리된 요청입니다.");
        }
        userClub.acceptJoin();
        return userClubRepository.save(userClub);
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




}
