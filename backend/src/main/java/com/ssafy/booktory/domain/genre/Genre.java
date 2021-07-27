package com.ssafy.booktory.domain.genre;

import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<ClubGenre> clubs = new ArrayList<>();
}
