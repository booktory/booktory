package com.ssafy.booktory.domain.club;

import com.ssafy.booktory.domain.club.Club;
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
    private Long leader_id;
    private String img;
    private String info;
    private int max_member;
    private boolean is_open;

    private int volum_rule;
    private int week_rule;
    private String free_rule;

    private List<Long> genres = new ArrayList<>();
    private List<Long> books = new ArrayList<>();


    @Builder
    public ClubSaveRequestDto(String name, Long leader_id, String img, String info, int max_member, boolean is_open, int volum_rule, int week_rule, String free_rule) {
        this.name = name;
        this.leader_id = leader_id;
        this.img = img;
        this.info = info;
        this.max_member = max_member;
        this.is_open = is_open;
        this.volum_rule = volum_rule;
        this.week_rule = week_rule;
        this.free_rule = free_rule;
    }

    public Club toEntity(User user){
        return Club.builder()
                .name(name)
                .user(user)
                .img(img)
                .info(info)
                .max_member(max_member)
                .is_open(is_open)
                .volum_rule(volum_rule)
                .week_rule(week_rule)
                .free_rule(free_rule)
                .build();
    }

}
