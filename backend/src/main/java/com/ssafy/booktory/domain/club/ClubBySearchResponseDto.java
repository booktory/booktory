package com.ssafy.booktory.domain.club;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClubBySearchResponseDto {

    private Long id;
    private String name;
    private String nickname;
    private String img;
    private int nowMember;
    private int maxMember;
    private List<Long> genres;

}
