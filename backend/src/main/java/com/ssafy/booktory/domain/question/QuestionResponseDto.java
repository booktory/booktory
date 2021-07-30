package com.ssafy.booktory.domain.question;

import com.ssafy.booktory.domain.answer.Answer;
import com.ssafy.booktory.domain.answer.AnswerResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QuestionResponseDto {

    private Long userId;
    private String nickname;
    private String profileImg;
    private String questionContents;
    private Boolean isOpen;
    private List<AnswerResponseDto> answers;

}
