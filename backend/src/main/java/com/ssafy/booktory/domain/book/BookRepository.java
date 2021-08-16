package com.ssafy.booktory.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book " +
            "WHERE title LIKE CONCAT('%', :keyword, '%') " +
            "OR author LIKE CONCAT('%', :keyword, '%') " +
            "ORDER BY " +
            "CASE WHEN title = :keyword THEN 0 " +
            "WHEN title LIKE CONCAT(:keyword, '%') THEN 1 " +
            "WHEN title LIKE CONCAT('%', :keyword, '%') THEN 2 " +
            "WHEN title LIKE CONCAT('%', :keyword) THEN 3 " +
            "ELSE 4 END " +
            "LIMIT 0, 100", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
