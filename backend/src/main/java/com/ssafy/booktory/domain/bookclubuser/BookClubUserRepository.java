package com.ssafy.booktory.domain.bookclubuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookClubUserRepository extends JpaRepository<BookClubUser, Long> {
    int countBookClubUserByUserId(Long userId);

    boolean existsByBookClubIdAndUserId(Long bookClubId, Long userId);

    BookClubUser findByBookClubIdAndUserId(Long bookClubId, Long userId);

    List<BookClubUser> findByBookClubIdAndJoiningTrue(Long bookClubId);
}
