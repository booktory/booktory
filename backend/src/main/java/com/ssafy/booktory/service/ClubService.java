package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.genre.Genre;
import com.ssafy.booktory.domain.genre.GenreRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
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

        User user = userRepository.findById(clubSaveRequestDto.getLeader_id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Club club = clubSaveRequestDto.toEntity(user);
        Club savedClub = clubRepository.save(club);

        List<BookClub> bookClubs = new ArrayList<>();
        for(Long bookId : clubSaveRequestDto.getBooks()){
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(()->new IllegalArgumentException("존재하지않는 책입니다."));
            BookClub bookClub = BookClub.builder()
                    .book(book)
                    .club(savedClub)
                    .build();
            bookClubs.add(bookClub);
        }
        savedClub.updateBookClubs(bookClubs);

        List<ClubGenre> clubGenres = new ArrayList<>();
        for(Long genreId : clubSaveRequestDto.getGenres()){
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(()-> new IllegalArgumentException("존재하지않는 장르입니다."));
            ClubGenre clubGenre = ClubGenre.builder()
                    .genre(genre)
                    .club(savedClub)
                    .build();
            clubGenres.add(clubGenre);
        }
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


}
