package com.ssafy.booktory.domain.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ssafy.booktory.domain.answer.Answer;
import com.ssafy.booktory.domain.answer.AnswerResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QuestionResponseDto {

    private Long questionId;
    private Long userId;
    private String nickname;
    private String profileImg;
    private String questionContents;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;
    private Boolean isOpen;
    private List<AnswerResponseDto> answers;

}
