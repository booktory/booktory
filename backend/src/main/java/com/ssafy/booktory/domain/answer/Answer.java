package com.ssafy.booktory.domain.answer;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.common.BaseTimeEntity;
import com.ssafy.booktory.domain.question.Question;
import com.ssafy.booktory.domain.user.User;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 1000)
    private String contents;

    @NotNull //입력이 없을 때 : 서비스로직에서 질문의 공개여부를 받아오기
    private boolean is_open;

    @Builder
    public Answer(String contents, boolean is_open) {
        this.contents = contents;
        this.is_open = is_open;
    }
}
