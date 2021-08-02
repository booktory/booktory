package com.ssafy.booktory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BooktoryApplicationTests {
//	@Autowired
//	ClubService clubService;
//
//	@Autowired
//	ClubRepository clubRepository;
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	EntityManager em;
//
//	@Test
//	@Rollback(value = false)
//	public void 새클럽등록() throws Exception{
//		//given
//		User user = User.builder()
//				.nickname("hi")
//				.email("abc@a.b")
//				.password("abc")
//				.build();
//		userRepository.save(user);
//
//		ClubSaveRequestDto clubSaveRequestDto = ClubSaveRequestDto.builder()
//				.name("testname")
//				.leader_id(user.getId())
//				.max_member(6)
//				.is_open(true)
//				.build();
//
//		//when
//		Club club = clubService.createClub(clubSaveRequestDto);
//
//		//then
//		em.flush();
//		Club resClub = clubRepository.findById(club.getId())
//				.orElseThrow(()-> new IllegalArgumentException("존재하지않는 클럽"));
//
//		assertEquals(club.getId(), resClub.getId());
//	}
	@Test
	void contextLoads() {
	}
}
