package com.ssafy.booktory.domain.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerResponseDto {

    private Long userId;
    private String nickname;
    private String profileImg;
    private String AnswerContents;
    private Boolean isOpen;

}
