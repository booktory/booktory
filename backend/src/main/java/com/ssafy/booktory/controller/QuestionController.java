package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.answer.AnswerRequestDto;
import com.ssafy.booktory.domain.question.QuestionRequestDto;
import com.ssafy.booktory.domain.question.QuestionResponseDto;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.service.QuestionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "Question API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @ApiOperation(value = "문의 게시판 목록 조회", notes = "모집 중인 클럽에 대한 문의와 답변 목록을 보여줍니다.")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionAnswer(@PathVariable @ApiParam(value = "클럽 아이디", required = true) Long clubId) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionAnswers(clubId));
    }

    @ApiOperation(value = "문의 게시판 질문 등록", notes = "모집 중인 클럽의 문의 게시판에 질문을 등록합니다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping("/{clubId}")
    public ResponseEntity<String> registerQuestion(@ApiIgnore final Authentication authentication,
                                                 @PathVariable @ApiParam(value = "클럽 아이디", required = true) Long clubId,
                                                 @RequestBody @ApiParam(value = "질문 등록에 필요한 정보", required = true) QuestionRequestDto questionRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        questionService.registerQuestion(user, clubId, questionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @ApiOperation(value = "문의 게시판 답 등록", notes = "모집 중인 클럽의 문의 게시판에 답변을 등록합니다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping("/{questionId}/answer")
    public ResponseEntity<String> registerAnswer(@ApiIgnore final Authentication authentication,
                                                 @PathVariable @ApiParam(value = "질문 아이디", required = true) Long questionId,
                                                 @RequestBody @ApiParam(value = "답변 등록에 필요한 정보", required = true) AnswerRequestDto answerRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = ((User)authentication.getPrincipal());
        questionService.registerAnswer(user, questionId, answerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }


}
