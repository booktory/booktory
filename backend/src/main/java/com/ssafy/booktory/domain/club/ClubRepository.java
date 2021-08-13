package com.ssafy.booktory.domain.club;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByNameContains(String keyword);

    int countClubByUserId(Long userId);

    boolean existsByName(String name);
}
