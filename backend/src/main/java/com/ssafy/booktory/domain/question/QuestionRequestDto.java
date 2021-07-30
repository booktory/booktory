package com.ssafy.booktory.domain.question;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRequestDto {

    private String contents;
    private Boolean isOpen;

}
