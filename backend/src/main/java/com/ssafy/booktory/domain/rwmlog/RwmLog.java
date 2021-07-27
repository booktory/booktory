package com.ssafy.booktory.domain.rwmlog;

import com.ssafy.booktory.domain.common.BaseTimeEntity;
import com.ssafy.booktory.domain.rwm.Rwm;
import com.ssafy.booktory.domain.user.User;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RwmLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rwm_id")
    private Rwm rwm;

    @NotNull
    private String book_name;

}
