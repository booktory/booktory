package com.ssafy.booktory.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {

    private String contents;
    private String fileUrl;

}