package com.ssafy.booktory.domain.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookByUserResponseDto {

    private Long id;
    private String title;
    private String thumbnail;

}
