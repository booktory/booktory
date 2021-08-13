package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.book.BookBySearchResponseDto;
import com.ssafy.booktory.domain.book.BookRepository;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubBySearchResponseDto;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.clubgenre.ClubGenreRepository;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ClubGenreRepository clubGenreRepository;
    private final ClubRepository clubRepository;
    private final UserClubRepository userClubRepository;
    private final BookRepository bookRepository;

    private List<ClubGenre> clubGenreList;
    private List<Club> clubList;

    @Transactional
    public List<ClubBySearchResponseDto> searchClub(String keyword, String genre) {
        if (genre != null) {
            List<String> searchGenres = new ArrayList<>(Arrays.asList(genre.split("\\+")));
            if (keyword != null) {
                clubGenreList = clubGenreRepository.findClubsByKeywordAndGenres(keyword, searchGenres);
            } else {
                clubGenreList = clubGenreRepository.findClubsByGenres(searchGenres);
            }
            clubList = new ArrayList<>(clubGenreList.stream()
                    .collect(Collectors.groupingBy(ClubGenre::getClub)).keySet());
        } else {
            clubList = clubRepository.findByNameContains(keyword);
        }

        List<ClubBySearchResponseDto> clubBySearchResponseDtos = new ArrayList<>();
        clubList.forEach(club -> {
            int nowMember = userClubRepository.findByClubIdAndState(club.getId(), UserClubState.ACCEPT).size();
            List<String> genreName = new ArrayList<>();
            club.getGenres().forEach(g -> {
                genreName.add(g.getGenre().getName());
            });

            clubBySearchResponseDtos.add(new ClubBySearchResponseDto(club.getId(),
                    club.getName(),
                    club.getUser().getNickname(),
                    club.getImg(),
                    nowMember,
                    club.getMaxMember(),
                    genreName));
        });

        return clubBySearchResponseDtos;
    }

    public List<BookBySearchResponseDto> searchBook(String keyword) {
        return bookRepository.findByKeyword(keyword).stream()
                .map(book -> new BookBySearchResponseDto(book.getId(),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getTranslators(),
                            book.getPublisher(),
                            book.getDate(),
                            book.getThumbnail())
                )
                .collect(Collectors.toList());
    }
}
