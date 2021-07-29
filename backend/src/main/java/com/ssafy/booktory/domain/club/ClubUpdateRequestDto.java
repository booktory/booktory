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
    String name;
    String img;
    String info;
    int maxMember;
    Boolean isOpen;
    int volumRule;
    int weekRule;
    String freeRule;

    List<Long> genres = new ArrayList<>();

    @Builder
    public ClubUpdateRequestDto(String name, String img, String info, int maxMember,
                                Boolean isOpen, int volumRule, int weekRule, String freeRule) {
        this.name = name;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.isOpen = isOpen;
        this.volumRule = volumRule;
        this.weekRule = weekRule;
        this.freeRule = freeRule;
    }
}
