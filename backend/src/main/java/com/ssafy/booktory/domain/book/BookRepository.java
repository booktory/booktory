package com.ssafy.booktory.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b where b.title like %:keyword% OR b.author like %:keyword%")
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
