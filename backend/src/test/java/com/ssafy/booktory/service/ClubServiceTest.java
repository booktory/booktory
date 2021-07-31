package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
import com.ssafy.booktory.domain.userclub.UserClub;
import com.ssafy.booktory.domain.userclub.UserClubRepository;
import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ClubServiceTest {
    @Autowired
    ClubService clubService;

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserClubRepository userClubRepository;
    @Autowired
    EntityManager em;

    private User user;
    private Club club;
    private User follower;
    private UserClub userClub;


    @Before
    private void setUp() throws Exception{
        user = User.builder()
                .nickname("hi")
                .email("abc@a.b")
                .password("abc")
                .build();
        user = userRepository.save(user);

        club = Club.builder()
                .name("testname")
                .user(user)
                .max_member(6)
                .build();
        club = clubRepository.save(club);

        follower = User.builder()
                .nickname("follow")
                .email("abc@a.b")
                .password("abcd")
                .build();
        follower = userRepository.save(follower);
    }

    @Test
    @Rollback(value = true)
    public void 새클럽등록() throws Exception{
        //given
        ClubSaveRequestDto clubSaveRequestDto = ClubSaveRequestDto.builder()
                .name("testname")
                .leaderId(3L)
                .maxMember(6)
                .isOpen(true)
                .build();

        //when
        Club club = clubService.createClub(clubSaveRequestDto);

        //then
        em.flush();
        Club resClub = clubRepository.findById(club.getId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지않는 클럽"));

        assertEquals(club.getId(), resClub.getId());
    }

    @Test
    @Rollback(value = true)
    public void 클럽정보_확인() throws Exception{
        //given
//        setup();
        ClubSaveRequestDto clubSaveRequestDto = ClubSaveRequestDto.builder()
                .name("testname")
                .leaderId(user.getId())
                .maxMember(6)
                .isOpen(true)
                .build();
        Club club1 = clubService.createClub(clubSaveRequestDto);

        //when
        Club club2 = clubService.findClub(club1.getId());
        em.flush();

        //then
        assertEquals(club1.getId(), club2.getId());
    }

    @Test
    @Rollback(value = true)
    public void 클럽_가입신청(){
        //given
//        setup();
        clubService.applyToClub(follower.getId(),  club.getId());

        //when
        UserClub userClub = userClubRepository.findByUserAndClub(follower, club);

        //then
        assertEquals(UserClubState.APPLY, userClub.getState());

    }

    @Test
    @Rollback(value = true)
    public void 클럽가입_승인_성공() throws Exception {
        //given
        setUp();
        userClub = UserClub.builder()
                .club(club)
                .user(follower)
                .state(UserClubState.APPLY)
                .build();
        userClub = userClubRepository.save(userClub);
        //when
        UserClub accepted = clubService.acceptToClub(user.getId(), club.getId(), userClub.getId(), true)
                .orElseThrow(()->new NullPointerException("존재하지 않는 데이터입니다."));

        //then
        assertEquals(accepted.getState(), UserClubState.ACCEPT);

    }

    @Test
    @Rollback(value = true)
    public void 클럽가입승인_실패() throws Exception {
        //given
        setUp();

        UserClub userClub = UserClub.builder()
                .club(club)
                .user(follower)
                .state(UserClubState.APPLY)
                .build();
        userClub = userClubRepository.save(userClub);

        //when
        UserClub finalUserClub = userClub;
        Exception exception = assertThrows(IllegalAccessException.class, ()->{
            clubService.acceptToClub(follower.getId(), club.getId(), finalUserClub.getId(), true);
        });

        //then
        String message = exception.getMessage();
        assertEquals("클럽장만 가입을 승인할 수 있습니다.", message);
    }
    @Test
    @Rollback(value = true)
    public void 클럽가입거절_성공() throws Exception {
        //given
        setUp();

        UserClub userClub = UserClub.builder()
                .club(club)
                .user(follower)
                .state(UserClubState.APPLY)
                .build();
        userClub = userClubRepository.save(userClub);
        Long id = userClub.getId();

        //when
        clubService.acceptToClub(user.getId(), club.getId(), userClub.getId(), false);

        //then
        assertEquals(Optional.empty(), userClubRepository.findById(id));
    }

    @Test
    @Rollback(value = true)
    public void 클럽삭제() throws Exception {
        //given
        setUp();

        //when
        clubService.deleteClub(user.getId(), club.getId());

        //then
        assertEquals(Optional.empty(), clubRepository.findById(club.getId()));
    }




}
