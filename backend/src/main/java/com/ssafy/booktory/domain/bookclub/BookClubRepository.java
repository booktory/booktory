package com.ssafy.booktory.domain.bookclub;

import com.ssafy.booktory.domain.book.Book;
import com.ssafy.booktory.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    Optional<BookClub> findByBookAndClub(Book book, Club club);
    List<BookClub> findByClub(Club club);
}
