package com.ssafy.booktory.controller;


import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.bookclub.BookClubAddRequestDto;
import com.ssafy.booktory.domain.bookclub.BookClubCreateRequestDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
import com.ssafy.booktory.service.BookClubService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "BookClub API")
@RestController
@RequestMapping("/bookclubs")
@RequiredArgsConstructor
public class BookClubController {

    private final BookClubService bookClubService;

    @PostMapping(produces="application/json;charset=UTF-8")
    @ApiOperation(value = "읽을 책 등록", notes = "새로운 읽을책(bookclub 테이블에)을 등록/생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> createBookToRead(@RequestBody BookClubCreateRequestDto bookclubCreateRequestDto){
        BookClub bookClub = bookClubService.createBookToRead(bookclubCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PutMapping()
    public ResponseEntity<String> addMeeting(@RequestBody BookClubAddRequestDto bookClubAddRequestDto){
        try {
            BookClub bookClub = bookClubService.addMeeting(bookClubAddRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> cancelMeeting(@PathVariable @ApiParam(value = "bookclub id", required = true) Long id){
        BookClub bookClub = bookClubService.cancelMeeting(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
