package com.ssafy.booktory.domain.userclub;

import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubListFindResponseDto;
import com.ssafy.booktory.domain.common.UserClubState;
import com.ssafy.booktory.domain.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    List<UserClub> findAllByUserAndState(User user, UserClubState state);

    UserClub findByUserAndClub(User user, Club club);
}
