
package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.board.BoardRepository;
import com.ssafy.booktory.domain.board.BoardRequestDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ClubRepository clubRepository;

    public Board registerBoard(User user, Long clubId, BoardRequestDto boardRequestDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 클럽입니다."));
        Board board = Board.builder()
                .user(user)
                .club(club)
                .contents(boardRequestDto.getContents())
                .fileUrl(boardRequestDto.getFileUrl())
                .build();
        return boardRepository.save(board);
    }
}