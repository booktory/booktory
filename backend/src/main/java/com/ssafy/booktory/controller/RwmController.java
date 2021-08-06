package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.rwm.RwmListResponseDto;
import com.ssafy.booktory.domain.rwm.RwmParticipantResponseDto;
import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.service.RwmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "Read With Me API")
@RestController
@RequestMapping("/api/rwms")
@RequiredArgsConstructor
public class RwmController {

    private final RwmService rwmService;

    @GetMapping()
    @ApiOperation(value = "RWM 리스트 조회", notes = "RWM 방 목록을 조회한다.")
    public ResponseEntity<List<RwmListResponseDto>> getRwmList(){
        return ResponseEntity.status(HttpStatus.OK).body(rwmService.getRwmList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "RWM 방 참가자 목록 조회", notes = "입력된 id에 해당하는 방의 참가자 목록을 조회한다.")
    public ResponseEntity<RwmParticipantResponseDto> getParticipant(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(rwmService.getParticipant(id));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "RWM방 입장", notes = "토큰에 저장된 사용자가 rwm방에 입자한다. (rwmLog 기록)")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> enterTheRwmRoom(@ApiIgnore final Authentication authentication, @PathVariable Long id, @RequestBody String bookName){
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = ((User)authentication.getPrincipal()).getId();

        rwmService.enterTheRoom(userId, id, bookName);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }


}
