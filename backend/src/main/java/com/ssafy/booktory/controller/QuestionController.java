package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.question.QuestionResponseDto;
import com.ssafy.booktory.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @ApiOperation(value = "문의 게시판 목록 조회", notes = "모집 중인 클럽에 대한 문의와 답변 목록을 보여줍니다.")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionAnswer(@PathVariable @ApiParam(value = "클럽 아이디", required = true) Long clubId) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionAnswers(clubId));
    }


}
