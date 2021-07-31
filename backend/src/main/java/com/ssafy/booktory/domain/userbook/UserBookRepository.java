package com.ssafy.booktory.domain.userbook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    List<UserBook> findByUserId(Long userId);
}
