package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.board.BoardRequestDto;
import com.ssafy.booktory.domain.board.BoardResponseDto;
import com.ssafy.booktory.domain.question.QuestionResponseDto;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.service.BoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "담벼락 글 등록", notes = "클럽 상세 페이지 내 담벼락에 글을 등록합니다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping("/{clubId}")
    public ResponseEntity<Void> registerBoard(@ApiIgnore final Authentication authentication,
                                              @PathVariable @ApiParam(value = "클럽 아이디", required = true) Long clubId,
                                              @RequestBody @ApiParam(value = "질문 등록에 필요한 정보") BoardRequestDto boardRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        Board board = boardService.registerBoard(user, clubId, boardRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "담벼락 전체 글 조회", notes = "클럽 상세 페이지 내 담벼락 전체 글을 보여줍니다.")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<BoardResponseDto>> getBoard(@PathVariable @ApiParam(value = "클럽 아이디", required = true) Long clubId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(clubId));
    }

    @ApiOperation(value = "담벼락 글 삭제", notes = "클럽 상세 페이지 내 담벼락에 등록한 글을 삭제합니다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@ApiIgnore final Authentication authentication,
                                            @PathVariable @ApiParam(value = "담벼락 아이디", required = true) Long boardId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        boardService.deleteBoard(boardId, user.getId());
        return ResponseEntity.ok().build();
    }

}