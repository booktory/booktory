package com.ssafy.booktory.domain.board;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.common.BaseTimeEntity;
import com.ssafy.booktory.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 5000)
    private String contents;

    private String fileUrl;

    @Builder
    public Board(Club club, User user, String contents, String fileUrl) {
        this.club = club;
        this.user = user;
        this.contents = contents;
        this.fileUrl = fileUrl;
    }

}
