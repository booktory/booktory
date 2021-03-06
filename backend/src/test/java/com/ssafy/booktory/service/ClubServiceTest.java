//package com.ssafy.booktory.service;
//
//import com.ssafy.booktory.domain.club.Club;
//import com.ssafy.booktory.domain.club.ClubFindResponseDto;
//import com.ssafy.booktory.domain.club.ClubRepository;
//import com.ssafy.booktory.domain.common.UserClubState;
//import com.ssafy.booktory.domain.user.User;
//import com.ssafy.booktory.domain.user.UserRepository;
//import com.ssafy.booktory.domain.club.ClubSaveRequestDto;
//import com.ssafy.booktory.domain.userclub.UserClub;
//import com.ssafy.booktory.domain.userclub.UserClubRepository;
//import org.junit.Before;
////import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import javax.persistence.EntityManager;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//class ClubServiceTest {
//
//    @Autowired ClubService clubService;
//    @Autowired ClubRepository clubRepository;
//    @Autowired UserRepository userRepository;
//    @Autowired UserClubRepository userClubRepository;
//
//    private User user;
//    private Club club;
//    private User follower;
//    private UserClub userClub;
//    private ClubSaveRequestDto clubSaveRequestDto;
//
//    @BeforeEach
//    private void setUp() throws Exception{
//        user = User.builder()
//                .nickname("hi")
//                .email("abc@a.b")
//                .password("abc")
//                .isAccept(true)
//                .build();
//        user = userRepository.save(user);
//        follower = User.builder()
//                .nickname("follow")
//                .email("abc@a.b")
//                .password("abcd")
//                .isAccept(true)
//                .build();
//        follower = userRepository.save(follower);
//
//        club = Club.builder()
//                .name("testname")
//                .user(user)
//                .maxMember(1)
//                .volumeRule(1)
//                .weekRule(3)
//                .isOpen(true)
//                .build();
//        club = clubRepository.save(club);
//
//        clubSaveRequestDto = ClubSaveRequestDto.builder()
//                .name("testname")
//                .maxMember(1)
//                .isOpen(true)
//                .build();
//    }
//
////    @Test
////    @Rollback(value = true)
////    public void ???????????????() throws Exception{
////        //given
////
////        //when
////        Club club = clubService.createClub(user.getId(), clubSaveRequestDto);
////
////        //then
////        Club resClub = clubRepository.findById(club.getId())
////                .orElseThrow(()-> new IllegalArgumentException("?????????????????? ??????"));
////
////        assertEquals(club.getId(), resClub.getId());
////    }
////
////    @Test
////    @Rollback(value = true)
////    public void ????????????_??????() throws Exception{
////        //given
////        Club club1 = clubService.createClub(user.getId(), clubSaveRequestDto);
////
////        //when
////        ClubFindResponseDto club2 = clubService.findClub(club1.getId());
////
////        //then
////        assertEquals(club1.getName(), club2.getName());
////    }
//
//    @Test
//    @Rollback(value = true)
//    public void ??????_????????????(){
//        //given
//        clubService.applyToClub(follower.getId(),  club.getId());
//
//        //when
//        UserClub userClub = userClubRepository.findByUserAndClub(follower, club);
//
//        //then
//        assertEquals(UserClubState.APPLY, userClub.getState());
//
//    }
//
//    @Test
//    @Rollback(value = true)
//    public void ????????????_??????_??????() throws Exception {
//        //given
//        userClub = UserClub.builder()
//                .club(club)
//                .user(follower)
//                .state(UserClubState.APPLY)
//                .build();
//        userClub = userClubRepository.save(userClub);
//        //when
//        UserClub accepted = clubService.acceptToClub(user.getId(), club.getId(), userClub.getId(), true)
//                .orElseThrow(()->new NullPointerException("???????????? ?????? ??????????????????."));
//
//        //then
//        assertEquals(accepted.getState(), UserClubState.ACCEPT);
//
//    }

////    @Test
////    @Rollback(value = true)
////    public void ????????????_??????_??????_????????????() throws Exception {
////        //given
////        Club club = clubService.createClub(user.getId(), clubSaveRequestDto);
////
////        userClub = UserClub.builder()
////                .club(club)
////                .user(follower)
////                .state(UserClubState.APPLY)
////                .build();
////        userClub = userClubRepository.save(userClub);
////
////        //when
////        UserClub finalUserClub = userClub;
////        Exception exception = assertThrows(Exception.class, ()->{
////            clubService.acceptToClub(user.getId(), club.getId(), finalUserClub.getId(), true);
////        });
////
////        //then
////        String message = exception.getMessage();
////        assertEquals("????????? ????????? ????????? ??? ????????????.", message);
////    }
//
//    @Test
//    @Rollback(value = true)
//    public void ??????????????????_??????_????????????() throws Exception {
//        //given
//        UserClub userClub = UserClub.builder()
//                .club(club)
//                .user(follower)
//                .state(UserClubState.APPLY)
//                .build();
//        userClub = userClubRepository.save(userClub);
//
//        //when
//        UserClub finalUserClub = userClub;
//        Exception exception = assertThrows(IllegalAccessException.class, ()->{
//            clubService.acceptToClub(follower.getId(), club.getId(), finalUserClub.getId(), true);
//        });
//
//        //then
//        String message = exception.getMessage();
//        assertEquals("???????????? ????????? ????????? ??? ????????????.", message);
//    }
//    @Test
//    @Rollback(value = true)
//    public void ??????????????????_??????() throws Exception {
//        //given
//
//        UserClub userClub = UserClub.builder()
//                .club(club)
//                .user(follower)
//                .state(UserClubState.APPLY)
//                .build();
//        userClub = userClubRepository.save(userClub);
//        Long id = userClub.getId();
//
//        //when
//        clubService.acceptToClub(user.getId(), club.getId(), userClub.getId(), false);
//
//        //then
//        assertEquals(Optional.empty(), userClubRepository.findById(id));
//    }
//
//    @Test
//    @Rollback(value = true)
//    public void ????????????() throws Exception {
//        //given
//
//        //when
//        clubService.deleteClub(user.getId(), club.getId());
//
//        //then
//        assertEquals(Optional.empty(), clubRepository.findById(club.getId()));
//    }
//
//
////    @Test
////    @Rollback(value = true)
////    public void ??????_??????_?????????_?????????_??????() throws Exception {
////        //given
////        Club club = clubService.createClub(user.getId(), clubSaveRequestDto);
////        clubService.applyToClub(follower.getId(), club.getId());
////
////        //when
////        List<UserClub> userClubs = userClubRepository.findAllByClub(club);
////
////        //then
////        assertEquals(2, userClubs.size());
////    }
//
//}
