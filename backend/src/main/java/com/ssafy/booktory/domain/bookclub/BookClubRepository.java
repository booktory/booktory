package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    Optional<BookClub> findByBookAndClub(Book book, Club club);
    List<BookClub> findByClub(Club club);

    List<BookClub> findByClubOrderByEndDatetimeDesc(Club club);

    @Query(value = "SELECT * FROM book_club WHERE club_id = :clubId AND end_datetime IS NOT NULL ORDER BY end_datetime DESC LIMIT 0, 1", nativeQuery = true)
    BookClub findByClubIdFirstByOrderByEndDatetimeDesc(Long clubId);

//    @Query("SELECT bc FROM BookClub bc WHERE bc.endDatetime >= :curDatetime AND bc.club.id = :clubId")
    @Query(value = "SELECT * FROM book_club WHERE end_datetime >= :curDatetime AND club_id = :clubId", nativeQuery = true)
    BookClub findByEndDatetimeGreaterThanCurDatetime(@Param("curDatetime") LocalDateTime curDatetime, @Param("clubId") Long clubId);

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

    Boolean existsByClubIdAndBookId(Long clubId, Long bookId);
}
