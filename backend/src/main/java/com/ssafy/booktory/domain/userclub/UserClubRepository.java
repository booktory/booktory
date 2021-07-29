package com.ssafy.booktory.domain.userclub;

import com.ssafy.booktory.domain.common.UserClubState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    List<UserClub> findByClubIdAndState(Long clubId, UserClubState state);
}
