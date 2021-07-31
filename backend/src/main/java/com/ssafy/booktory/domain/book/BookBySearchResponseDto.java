package com.ssafy.booktory.domain.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookBySearchResponseDto {

    private Long id;
    private String title;
    private String author;
    private String translators;
    private String publisher;
    private LocalDateTime date;
    private String thumbnail;

}
