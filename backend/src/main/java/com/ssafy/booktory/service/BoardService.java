
package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.board.BoardRepository;
import com.ssafy.booktory.domain.board.BoardRequestDto;
import com.ssafy.booktory.domain.board.BoardResponseDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    @Transactional
    public List<BoardResponseDto> getBoard(Long clubId) {
        return boardRepository.findByClubId(clubId).stream()
                .map(board -> new BoardResponseDto(
                        board.getId(),
                        board.getUser().getId(),
                        board.getUser().getNickname(),
                        board.getUser().getProfileImg(),
                        board.getContents(),
                        board.getCreatedDate(),
                        board.getFileUrl()
                ))
                .collect(Collectors.toList());
    }
}