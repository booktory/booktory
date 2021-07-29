package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubFindResponseDto;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
import com.ssafy.booktory.service.ClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.status;

@Api(value = "Club API")
@RestController
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping(produces="application/json;charset=UTF-8")
    @ApiOperation(value = "새 클럽 등록", notes = "새로운 클럽을 등록/생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Void> createClub(@RequestBody ClubSaveRequestDto clubSaveRequestDto){
        Club club = clubService.createClub(clubSaveRequestDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "클럽정보 확인", notes = "")
    public ResponseEntity<ClubFindResponseDto> findClub(@PathVariable Long id){
        Club club = clubService.findClub(id);
        ClubFindResponseDto clubFindResponseDto = new ClubFindResponseDto(club);
        return ResponseEntity.status(HttpStatus.OK).body(clubFindResponseDto);
    }


}
