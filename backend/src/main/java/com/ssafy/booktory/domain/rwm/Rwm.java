package com.ssafy.booktory.domain.rwm;

import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rwm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotNull
    private String name;

    @NotNull
    private String info;

    @NotNull
    private String img;

    @NotNull
    private String api_link;

    @OneToMany(mappedBy = "rwm", cascade = CascadeType.ALL)
    private List<RwmLog> logs = new ArrayList<>();

    @Builder
    public Rwm(String name, String info, String img, String api_link) {
        this.name = name;
        this.info = info;
        this.img = img;
        this.api_link = api_link;
    }
}
