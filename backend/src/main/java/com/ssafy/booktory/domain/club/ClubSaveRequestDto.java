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
    private Long leaderId;
    private String img;
    private String info;
    private int maxMember;
    private Boolean isOpen;

    private int volumeRule;
    private int weekRule;
    private String freeRule;

    private List<Long> genres = new ArrayList<>();
    private List<Long> books = new ArrayList<>();


    @Builder
    public ClubSaveRequestDto(String name, Long leaderId, String img, String info, int maxMember, boolean isOpen, int volumeRule, int weekRule, String freeRule) {
        this.name = name;
        this.leaderId = leaderId;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.isOpen = isOpen;
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
                .isOpen(isOpen)
                .volumeRule(volumeRule)
                .weekRule(weekRule)
                .freeRule(freeRule)
                .build();
    }

}
