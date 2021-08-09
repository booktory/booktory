package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    Optional<BookClub> findByBookAndClub(Book book, Club club);
    List<BookClub> findByClub(Club club);

    @Query(value = "SELECT COUNT(*) " +
            "FROM book_club AS a " +
            "JOIN book_club_user AS b ON a.id = b.bookclub_id " +
            "WHERE end_datetime BETWEEN DATE_ADD(now(), INTERVAL -1 WEEK) and now() " +
            "AND b.user_id = :userId", nativeQuery = true)
    int countBookClubFromWeekByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(*) " +
            "FROM book_club AS a " +
            "JOIN book_club_user AS b ON a.id = b.bookclub_id " +
            "WHERE end_datetime BETWEEN DATE_ADD(now(), INTERVAL -1 DAY) and now() " +
            "AND b.user_id = :userId", nativeQuery = true)
    int countBookClubFromDayByUserId(@Param("userId") Long userId);

}
