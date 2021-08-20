package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.book.BookBySearchResponseDto;
import com.ssafy.booktory.domain.club.ClubBySearchResponseDto;
import com.ssafy.booktory.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Search API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "클럽 검색", notes = "클럽명과 장르로 찾고자 하는 클럽을 검색한다.")
    @GetMapping("/clubs")
    public ResponseEntity<List<ClubBySearchResponseDto>> searchClubByNameAndGenre(@RequestParam(required = false) @ApiParam(value = "클럽명 검색 키워드") String keyword,
                                                                              @RequestParam(required = false) @ApiParam(value = "장르 이름 조합", example="소설+시+요리+") String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.searchClub(keyword, genre));
    }

    @ApiOperation(value = "책 검색", notes = "검색 키워드를 입력 받아, 비슷한 이름의 책 제목과 저자를 포함하는 책을 검색한다.")
    @GetMapping("/books")
    public ResponseEntity<List<BookBySearchResponseDto>> searchBookByKeyword(@RequestParam(required = false) @ApiParam(value = "책 이름 또는 저자 검색 키워드") String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.searchBook(keyword));
    }

}
