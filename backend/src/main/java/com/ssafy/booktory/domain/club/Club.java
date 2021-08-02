package com.ssafy.booktory.domain.club;


import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.bookclub.BookClub;
import com.ssafy.booktory.domain.clubgenre.ClubGenre;
import com.ssafy.booktory.domain.common.BaseTimeEntity;
import com.ssafy.booktory.domain.question.Question;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private User user;

    private String img;

    @Column(length = 100)
    private String info;

    @NotNull
    @ColumnDefault("8")
    private int maxMember;

    @NotNull
    @ColumnDefault("true")
    private Boolean isOpen;

    private int volumeRule;
    private int weekRule;

    @Column(length = 100)
    private String freeRule;


    //클럽에 가입한 멤버들
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<UserClub> members = new ArrayList<>();

    //클럽에 작성된 담벼락
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<BookClub> bookClubs = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ClubGenre> genres = new ArrayList<>();

    public void updateClub(String name, String img, String info, int maxMember,
                           Boolean isOpen, int volumRule, int weekRule, String freeRule) {
        this.name = name;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.isOpen = isOpen;
        this.volumeRule = volumRule;
        this.weekRule = weekRule;
        this.freeRule = freeRule;
    }

    public void updateBookClubs(List<BookClub> bookClubs){
//        for(BookClub bookClub : bookClubs)
//            this.bookClubs.add(bookClub);
        this.bookClubs = bookClubs;
    }
    public void updateGenres(List<ClubGenre> genres){
//        for(ClubGenre clubGenre : genres)
//            this.genres.add(clubGenre);
        this.genres = genres;
    }

    @Builder
    public Club(Long id, String name, User user, String img, String info, int maxMember
            , Boolean isOpen, int volumeRule, int weekRule, String freeRule) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.img = img;
        this.info = info;
        this.maxMember = maxMember;
        this.isOpen = isOpen;
        this.volumeRule = volumeRule;
        this.weekRule = weekRule;
        this.freeRule = freeRule;
    }

}
