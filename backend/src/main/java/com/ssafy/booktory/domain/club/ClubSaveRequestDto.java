package com.ssafy.booktory.domain.club;

import com.ssafy.booktory.domain.user.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("")
public class ClubSaveRequestDto {
    private String name;
    private String img;
    private String info;
    private int maxMember;

    private int volumeRule;
    private int weekRule;
    private String freeRule;

    private List<Long> genres = new ArrayList<>();
    private List<Long> books = new ArrayList<>();


    @Builder
    public ClubSaveRequestDto(String name, String img, String info, int maxMember, int volumeRule, int weekRule, String freeRule) {
        this.name = name;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.volumeRule = volumeRule;
        this.weekRule = weekRule;
        this.freeRule = freeRule;
    }

    public Club toEntity(User user){
        return Club.builder()
                .name(name)
                .user(user)
                .img(img)
                .info(info)
                .maxMember(maxMember)
                .volumeRule(volumeRule)
                .weekRule(weekRule)
                .freeRule(freeRule)
                .build();
    }

}
