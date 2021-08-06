package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.rwm.RwmListResponseDto;
import com.ssafy.booktory.domain.rwm.RwmParticipantResponseDto;
import com.ssafy.booktory.service.RwmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
