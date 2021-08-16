package com.ssafy.booktory.controller;


import com.ssafy.booktory.domain.bookclub.*;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.service.BookClubService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "BookClub API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookclubs")
@RequiredArgsConstructor
public class BookClubController {

    private final BookClubService bookClubService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "읽을 책 등록", notes = "새로운 읽을책(bookclub 테이블에)을 등록/생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> createBookToRead(@RequestBody BookClubCreateRequestDto bookclubCreateRequestDto) {
        bookClubService.createBookToRead(bookclubCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @PutMapping()
    @ApiOperation(value = "모임 등록", notes = "추가된 읽을책에 모임 시간을 추가한다.")
    public ResponseEntity<String> addMeeting(@RequestBody BookClubAddRequestDto bookClubAddRequestDto) {
        try {
            BookClub bookClub = bookClubService.addMeeting(bookClubAddRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "모임 취소", notes = "읽을 책은 그대로 두고 모임 예정시간만 삭제한다.")
    public ResponseEntity<String> cancelMeeting(@PathVariable @ApiParam(value = "bookclub id", required = true) Long id) {
        BookClub bookClub = bookClubService.cancelMeeting(id);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/list/{clubId}")
    @ApiOperation(value = "모임/읽을책 목록 확인", notes = "해당클럽의 모임 목록을 확인한다.")
    public ResponseEntity<List<BookClubListResponseDto>> bookClubList(@PathVariable Long clubId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookClubService.findBookClubList(clubId));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "모임/읽을책 삭제", notes = "모임/읽을책을 삭제한다.")
    public ResponseEntity<String> deleteBookClub(@PathVariable Long id) {
        bookClubService.deleteBookClub(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }

    @PostMapping("/{id}/user")
    @ApiOperation(value = "모임 참가", notes = "현재 사용자가 모임에 참가한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> attendToMeeting(@ApiIgnore final Authentication authentication, @PathVariable Long id) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User) authentication.getPrincipal()).getId();

        bookClubService.attendToMeeting(id, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "모임/읽을책 정보 확인(1개)", notes = "모임/읽을책 정보를 1개 확인한다.")
    public ResponseEntity<BookClubInfoResponseDto> bookClubInfo(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookClubService.bookClubInfo(id));
    }

}