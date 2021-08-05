package com.ssafy.booktory.controller;

import com.ssafy.booktory.util.Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api("File Upload API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("api/files")
public class FileUploadController {

    private final Uploader uploader;
    private final Long FILE_LIMIT_SIZE = (long)1e7;

    @PostMapping( value = "/user", produces="application/json;charset=UTF-8")
    @ApiOperation(value = "프로필 이미지 등록", notes = "프로필 이미지를 S3원격 서버에 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 400, message = "파일용량 초과 / 확장자 오류"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> uploadProfileImg(@RequestParam("file") MultipartFile file) throws IOException{
        log.info(String.format("업로드 파일 size : %d", file.getSize()));
        if(file.getSize() > FILE_LIMIT_SIZE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일용량 초과");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(uploader.uploadS3Instance(file, "static/user"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/board")
    @ApiOperation(value = "담벼락 파일 등록", notes = "담벼락에 업로드된 파일을 S3원격 서버에 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 400, message = "파일용량 초과"),
            @ApiResponse(code = 401, message = "인증실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> uploadBoardFile(@RequestParam("file") MultipartFile file) throws Exception {
        log.info(String.format("업로드 파일 size : %d", file.getSize()));
        if(file.getSize() > FILE_LIMIT_SIZE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일용량 초과");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(uploader.uploadS3Instance(file, "static/board"));
    }


}