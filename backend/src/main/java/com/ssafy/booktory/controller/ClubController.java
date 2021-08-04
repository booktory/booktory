package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.club.*;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.userclub.UserClubListResponseDto;
import com.ssafy.booktory.service.ClubService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "Club API")
@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping(produces="application/json;charset=UTF-8")
    @ApiOperation(value = "새 클럽 등록", notes = "새로운 클럽을 등록/생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> createClub(@ApiIgnore final Authentication authentication, @RequestBody ClubSaveRequestDto clubSaveRequestDto){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();
        Club club = clubService.createClub(userId, clubSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "클럽정보 확인", notes = "해당 클럽의 정보를 모두 반환한다.")
    public ResponseEntity<ClubFindResponseDto> findClub(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clubService.findClub(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "클럽목록 확인", notes = "해당 유저가 가입한 클럽들을 보여준다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<List<ClubListFindResponseDto>> findJoinedClubList(@ApiIgnore final Authentication authentication){

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(clubService.findJoinedClubList(userId));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "클럽 정보 수정" , notes = "클럽의 정보를 수정한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> updateClub(@ApiIgnore final Authentication authentication, @PathVariable Long id, @RequestBody ClubUpdateRequestDto clubUpdateRequestDto){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long leaderId = ((User)authentication.getPrincipal()).getId();

        try {
            clubService.updateClub(id, clubUpdateRequestDto, leaderId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/{id}/join")
    @ApiOperation(value = "클럽 가입신청", notes = "UserClub 테이블의 상태를 APPLY로 등록한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<Long>  applyToClub(@ApiIgnore final Authentication authentication, @PathVariable Long id){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.applyToClub(userId, id).getId());
    }

    @GetMapping("{id}/users")
    @ApiOperation(value = "클럽 가입/신청자 목록", notes = "UserClub 테이블에서 해당 클럽에 연관된 데이터를 모두 조회한다.")
    public ResponseEntity<UserClubListResponseDto> joinedUserList(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clubService.joinedUserList(id));
    }


    @PutMapping("/{id}/join")
    @ApiOperation(value = "클럽 가입승인", notes = "UserClub 테이블의 상태를 ACCEPT로 변경한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String>  acceptToClub(@ApiIgnore final Authentication authentication, @PathVariable Long id, @RequestBody Long userClubId){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long leaderId = ((User)authentication.getPrincipal()).getId();

        try {
            clubService.acceptToClub(leaderId, id, userClubId, true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @DeleteMapping("/{id}/join/{userClubId}")
    @ApiOperation(value = "클럽 가입거절", notes = "UserClub 테이블의 데이터를 삭제한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> rejectJoin(@ApiIgnore final Authentication authentication, @PathVariable Long id, @PathVariable Long userClubId){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long leaderId = ((User)authentication.getPrincipal()).getId();

        try {
            clubService.acceptToClub(leaderId, id, userClubId, false);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }

    @DeleteMapping("/{id}/user")
    @ApiOperation(value = "클럽 탈퇴", notes = "본인의 UserClub 테이블의 데이터를 삭제한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> deleteJoin(@ApiIgnore final Authentication authentication, @PathVariable Long id){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();

        try {
            clubService.deleteJoin(userId, id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "클럽 삭제", notes = "클럽장이 클럽을 삭제한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> deleteClub(@ApiIgnore final Authentication authentication, @PathVariable Long id){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long leaderId = ((User)authentication.getPrincipal()).getId();

        try {
            clubService.deleteClub(leaderId, id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }

}
