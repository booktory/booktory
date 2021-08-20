
package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.board.BoardRepository;
import com.ssafy.booktory.domain.board.BoardRequestDto;
import com.ssafy.booktory.domain.board.BoardResponseDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.util.Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ClubRepository clubRepository;
    private final Uploader uploader;
    private final NotificationService notificationService;

    public void registerBoard(User user, Long clubId, BoardRequestDto boardRequestDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 클럽입니다."));
        Board board = Board.builder()
                .user(user)
                .club(club)
                .contents(boardRequestDto.getContents())
                .fileUrl(boardRequestDto.getFileUrl())
                .build();
        boardRepository.save(board);

        int boardCnt = boardRepository.countBoardByUserId(user.getId());
        if (boardCnt == 1) {
            notificationService.makeBadgeNotification(12, user);
        }
        if (boardCnt == 10) {
            notificationService.makeBadgeNotification(13, user);
        }
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
                        board.getCreatedDate().plusHours(9L),
                        board.getFileUrl()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBoard(Long id, Long userId) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 글입니다."));

        //file 인스턴스 삭제
        String fileUrl = board.getFileUrl();
        log.info("삭제할 파일 :" + fileUrl);
        if(fileUrl != null) uploader.deleteS3Instance(fileUrl);

        if (board.getUser().getId() == userId) {
            boardRepository.delete(board);
        }
    }
}