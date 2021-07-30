package com.ssafy.booktory.domain.answer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerRequestDto {

    private String contents;
    private Boolean isOpen;

}