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
    List<ClubListArg> clubs = new ArrayList<>();

    public void toDto(List<Club> clubs){
        for(Club club : clubs){
            ClubListArg clubListArg = new ClubListArg(club.getId(), club.getName());
            this.clubs.add(clubListArg);
        }
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class ClubListArg{
    Long id;
    String name;
}