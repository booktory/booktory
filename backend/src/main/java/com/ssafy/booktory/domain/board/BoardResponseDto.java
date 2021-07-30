package com.ssafy.booktory.domain.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private Long userId;
    private String nickname;
    private String profileImg;
    private String contents;
    private LocalDateTime date;
    private String fileUrl;

}