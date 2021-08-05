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
import com.ssafy.booktory.domain.userclub.UserClubListResponseDto;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserClubRepository userClubRepository;

    public Club createClub(Long userId, ClubSaveRequestDto clubSaveRequestDto){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다."));

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

    @Transactional
    public ClubFindResponseDto findClub(Long id){
        Club club = clubRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));
        int nowMember = getClubMembersCount(club);
        return new ClubFindResponseDto(club, nowMember);
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

    public Club updateClub(Long id, ClubUpdateRequestDto clubUpdateRequestDto, Long leaderId) throws Exception {
        Club club = clubRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));
        User leader = userRepository.findById(leaderId)
                .orElseThrow(()-> new NoSuchElementException("사용자 정보가 존재하지 않습니다."));
        if(club.getUser().getId() != leader.getId())
            throw new IllegalAccessException("클럽장만 클럽 정보를 수정할 수 있습니다.");

        if(getClubMembersCount(club) < clubUpdateRequestDto.getMaxMember())
            throw new IllegalArgumentException("최대 수용멤버를 현재 멤버 수 보다 적게 설정할 수 없습니다.");

        club.updateClub(clubUpdateRequestDto.getName(), clubUpdateRequestDto.getImg(), clubUpdateRequestDto.getInfo(),
                clubUpdateRequestDto.getMaxMember(), clubUpdateRequestDto.getIsOpen(),
                clubUpdateRequestDto.getVolumeRule(), clubUpdateRequestDto.getWeekRule(), clubUpdateRequestDto.getFreeRule());

        List<ClubGenre> clubGenres = genreIdListToClubGenreList(clubUpdateRequestDto.getGenres(), club);
        club.updateGenres(clubGenres);

        return clubRepository.save(club);
    }

    public UserClub applyToClub(Long userId, Long clubId) {
        Club club = clubRepository.findById(clubId)
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
    public Optional<UserClub> acceptToClub(Long leaderId, Long clubId, Long userClubId, boolean isAccept) throws Exception{
        UserClub userClub = userClubRepository.findById(userClubId)
                .orElseThrow(()->new NoSuchElementException("존재하지않는 가입신청입니다."));

        if(clubId != userClub.getClub().getId() )
            throw new IllegalAccessException("클럽에 접근권한이 없습니다.");
        if(leaderId != userClub.getClub().getUser().getId())
            throw new IllegalAccessException("클럽장만 가입을 승인할 수 있습니다.");
        if(userClub.getState() == UserClubState.ACCEPT)
            throw new IllegalStateException("이미 처리된 요청입니다.");
        if(!isAccept) {
            log.info("---------------???---------"+getClubMembersCount(userClub.getClub()) + "\n\n");
            log.info("--------------????----------"+userClub.getClub().getMaxMember() + "\n\n");

            userClubRepository.delete(userClub);
            return null;
        }
        log.info("------------------------"+getClubMembersCount(userClub.getClub()) + "\n\n");
        log.info("------------------------"+userClub.getClub().getMaxMember() + "\n\n");
        if(getClubMembersCount(userClub.getClub()) >= userClub.getClub().getMaxMember())
            throw new IllegalArgumentException("멤버를 더이상 수용할 수 없습니다.");
        userClub.acceptJoin();
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

    public void deleteClub(Long leaderId, Long clubId) throws Exception {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 클럽입니다."));

        if(club.getUser().getId() != leaderId){
            throw new IllegalAccessException("클럽 삭제 권한이 없습니다.");
        }
        clubRepository.delete(club);
    }

    public UserClubListResponseDto joinedUserList(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()->new NoSuchElementException("존재하지 않는 클럽입니다."));

        UserClubListResponseDto userClubListResponseDto = new UserClubListResponseDto();
        userClubListResponseDto.toDto(userClubRepository.findAllByClub(club));
        return userClubListResponseDto;
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


}
