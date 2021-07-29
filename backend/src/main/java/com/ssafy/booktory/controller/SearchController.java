package com.ssafy.booktory.controller;

import com.ssafy.booktory.domain.club.ClubBySearchResponseDto;
import com.ssafy.booktory.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "클럽 검색", notes = "클럽명과 장르로 찾고자 하는 클럽을 검색한다.")
    @GetMapping()
    public ResponseEntity<List<ClubBySearchResponseDto>> searchByNameAndGenre(@RequestParam(required = false) @ApiParam(value = "클럽명 검색 키워드") String keyword,
                                                                              @RequestParam(required = false) @ApiParam(value = "장르 이름 조합", example="소설+시+요리+") String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.searchClub(keyword, genre));
    }

}
