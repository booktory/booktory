package com.ssafy.booktory.domain.club;

import com.ssafy.booktory.domain.userclub.UserClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubListFindResponseDto {

    private Long id;
    private String name;

    public ClubListFindResponseDto(Club club){
        this.id = club.getId();
        this.name = club.getName();
    }
}