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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("clubService")
public class ClubService {
    public static final Logger logger = LoggerFactory.getLogger(ClubService.class);

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    UserClubRepository userClubRepository;

    public Club createClub(ClubSaveRequestDto clubSaveRequestDto){

        User user = userRepository.findById(clubSaveRequestDto.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        logger.debug("user : " + user.getId() + " - " + user.getName());
        Club club = clubSaveRequestDto.toEntity(user);
        Club savedClub = clubRepository.save(club);
        logger.debug("-------1------");

        List<BookClub> bookClubs = bookIdListToBookClubList(clubSaveRequestDto.getBooks(), savedClub);
        savedClub.updateBookClubs(bookClubs);

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubSaveRequestDto.getGenres(), savedClub);
        savedClub.updateGenres(clubGenres);

        logger.debug("-------2------");
        UserClub userClub = UserClub.builder()
                .club(savedClub)
                .user(user)
                .state(UserClubState.ACCEPT)
                .build();
        userClubRepository.save(userClub);

        logger.debug("-------3------");

        return clubRepository.save(savedClub);
    }

    public Club findClub(Long id){
        return clubRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));
    }

    public ClubListFindResponseDto findJoinedClubList(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 회원입니다."));
        List<UserClub> userClubs = userClubRepository.findAllByUserAndState(user, UserClubState.ACCEPT);

        List<Club> clubs = new ArrayList<>();
        for(UserClub userClub : userClubs){
            Club club = clubRepository.findById(userClub.getClub().getId())
                    .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));
            clubs.add(club);

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
