package com.ssafy.booktory.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ssafy.booktory.domain.answer.Answer;
import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.bookclubuser.BookClubUser;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.notification.Notification;
import com.ssafy.booktory.domain.question.Question;
import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.ssafy.booktory.domain.userbook.UserBook;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotNull
    private String email;

    @Column(length = 20)
    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @Column(length = 20)
    private String name;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String profileImg;

    @Column(length = 30)
    private String phone;

    @ColumnDefault("0")
    private int badge;

    @ColumnDefault("-1")
    private int mainBadge;

    @NotNull
    @ColumnDefault("false")
    private Boolean isAccept;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //내가 리더로 있는 클럽
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Club> leaderClubs = new ArrayList<>();

    //가입한 클럽(리더로 있는 클럽도 포함)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserClub> memberClubs = new ArrayList<>();

    //내가 작성한 담벼락
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookClubUser> bookClubUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBook> books = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RwmLog> rwmLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @Builder
    public User(String email, String nickname, String password, String name, LocalDate birth,
                String profileImg, String phone, int badge, int mainBadge, Boolean isAccept, SocialType socialType){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.profileImg = profileImg;
        this.phone = phone;
        this.badge = badge;
        this.mainBadge = mainBadge;
        this.isAccept = isAccept;
        this.socialType = socialType;
    }

    public void addExtraInfo(String name, LocalDate birth, String profileImg, String phone) {
        this.name = name;
        this.birth = birth;
        this.profileImg = profileImg;
        this.phone = phone;
    }

    public void update(String nickname, String name, LocalDate birth, String profileImg, String phone) {
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
        this.profileImg = profileImg;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void setMainBadge(int badgeId) {
        this.mainBadge = badgeId;
    }

    public void updateAcceptState(Boolean state) {
        this.isAccept = state;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Integer> getBadgeList(int badge) {
        List<Integer> badges = new ArrayList<>();
        String badgeStatus = String.format("%015d", Long.parseLong(Long.toBinaryString(badge)));
        for (int i = 0; i < 15; i++) {
            if (badgeStatus.charAt(i) == '1') {
                badges.add(i);
            }
        }
        return badges;
    }

    public boolean isExistBadge(int badgeId) {
        String badgeStatus = String.format("%015d", Long.parseLong(Long.toBinaryString(badge)));
        return badgeStatus.charAt(badgeId) == '1';
    }

    public void updateBadgeStatus(int badgeId) {
        String badgeStatus = String.format("%015d", Long.parseLong(Long.toBinaryString(badge)));
        char[] tmpBadge = badgeStatus.toCharArray();
        tmpBadge[badgeId] = '1';
        String newBadgeStatus = String.valueOf(tmpBadge);
        this.badge = Integer.parseInt(newBadgeStatus, 2);
    }
}
