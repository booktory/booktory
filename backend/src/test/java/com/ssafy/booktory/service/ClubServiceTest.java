package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

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
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 새클럽등록() throws Exception{
        //given
        User user = User.builder()
                .nickname("hi")
                .email("abc@a.b")
                .password("abc")
                .build();
        userRepository.save(user);

        ClubSaveRequestDto clubSaveRequestDto = ClubSaveRequestDto.builder()
                .name("testname")
                .leader_id(user.getId())
                .max_member(6)
                .is_open(true)
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
    @Rollback(value = false)
    public void 클럽정보_확인() throws Exception{
        //given
        User user = User.builder()
                .nickname("hi")
                .email("abc@a.b")
                .password("abc")
                .build();
        userRepository.save(user);

        ClubSaveRequestDto clubSaveRequestDto = ClubSaveRequestDto.builder()
                .name("testname")
                .leader_id(user.getId())
                .max_member(6)
                .is_open(true)
                .build();
        Club club1 = clubService.createClub(clubSaveRequestDto);

        //when
        Club club2 = clubService.findClub(club1.getId());

        //then
        em.flush();
        assertEquals(club1.getId(), club2.getId());
    }

}