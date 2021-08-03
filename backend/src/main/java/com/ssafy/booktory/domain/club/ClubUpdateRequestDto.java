package com.ssafy.booktory.domain.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubUpdateRequestDto {
    private String name;
    private String img;
    private String info;
    private int maxMember;
    private Boolean isOpen;
    private int volumeRule;
    private int weekRule;
    private String freeRule;

    List<Long> genres = new ArrayList<>();

    @Builder
    public ClubUpdateRequestDto(String name, String img, String info, int maxMember,
                                Boolean isOpen, int volumeRule, int weekRule, String freeRule) {
        this.name = name;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.isOpen = isOpen;
        this.volumeRule = volumeRule;
        this.weekRule = weekRule;
        this.freeRule = freeRule;
    }
}
