package com.ssafy.booktory.domain.userclub;

import com.ssafy.booktory.domain.common.UserClubState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class UserClubListResponseDto {
    List<UserClubListArg> userClubs = new ArrayList<>();

    public void toDto(List<UserClub> userClubs){
        for(UserClub userClub : userClubs){
            UserClubListArg userClubListArg =
                    new UserClubListArg(userClub.getId(), userClub.getUser().getId(), userClub.getState());
            this.userClubs.add(userClubListArg);
        }
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserClubListArg{
    Long id;
    Long userId;
    UserClubState staate;
}