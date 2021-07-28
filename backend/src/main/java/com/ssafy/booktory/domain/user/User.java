package com.ssafy.booktory.domain.user;

import com.ssafy.booktory.domain.answer.Answer;
import com.ssafy.booktory.domain.board.Board;
import com.ssafy.booktory.domain.bookclubuser.BookClubUser;
import com.ssafy.booktory.domain.club.Club;
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

    private LocalDate birth;

    private String profile_img;

    @Column(length = 30)
    private String phone;

    @ColumnDefault("0")
    private int badge;

    @ColumnDefault("-1")
    private int main_badge;

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
                String profile_img, String phone, int badge, int main_badge){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.profile_img = profile_img;
        this.phone = phone;
        this.badge = badge;
        this.main_badge = main_badge;
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
}
