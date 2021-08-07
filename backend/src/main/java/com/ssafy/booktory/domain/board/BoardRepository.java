package com.ssafy.booktory.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByClubId(Long clubId);
    int countBoardByUserId(Long userId);
}