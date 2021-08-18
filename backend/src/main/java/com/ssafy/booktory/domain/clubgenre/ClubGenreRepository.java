package com.ssafy.booktory.domain.clubgenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubGenreRepository extends JpaRepository<ClubGenre, Long> {

    @Query("SELECT cg FROM ClubGenre cg JOIN cg.club c JOIN cg.genre g " +
            "WHERE c.name LIKE %:keyword% " +
            "AND g.name IN (:searchGenres) " +
            "ORDER BY cg.club.id")
    List<ClubGenre> findClubsByKeywordAndGenresOrderByClubAsc(@Param("keyword") String keyword, @Param("searchGenres") List<String> searchGenres);

    @Query("SELECT cg FROM ClubGenre cg JOIN cg.club c JOIN cg.genre g " +
            "WHERE g.name IN (:searchGenres) " +
            "ORDER BY cg.club.id ASC")
    List<ClubGenre> findClubsByGenresOrderByClubAsc(List<String> searchGenres);

    List<ClubGenre> findByClubId(Long clubId);
}
