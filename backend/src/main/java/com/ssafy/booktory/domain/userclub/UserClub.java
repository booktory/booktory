package com.ssafy.booktory.domain.userclub;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.user.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UserClub {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(30) default 'APPLY'")
    @NotNull
    private UserClubState state;

//    @Builder
//    public UserClub(UserClubState state) {
//        this.state = state;
//    }
}
