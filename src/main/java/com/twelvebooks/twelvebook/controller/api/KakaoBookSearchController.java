package com.twelvebooks.twelvebook.controller.api;

import com.twelvebooks.twelvebook.dto.SearchResultDTO;
import com.twelvebooks.twelvebook.service.KakaoBookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/searchBooks")
public class KakaoBookSearchController {
    @Autowired
    KakaoBookApiService kakaoBookApiService;

//    @GetMapping
//    public Map<String, Object> searchBooks(@RequestParam("searchWord") String searchWord,
//                                           @RequestParam(name = "target", defaultValue = "all") String target,
//                                           @RequestParam(name = "category", defaultValue = "") String category,
//                                           @RequestParam(name = "page", defaultValue = "1") int page) {
//
//        Map<String, Object> result = kakaoBookApiService.searchBooks(searchWord, target, category, page);
//
//        return result;
//    }

    @GetMapping
    public ResponseEntity<Mono<SearchResultDTO>> searchBooks(@RequestParam(name = "searchWord") String searchWord,
                                                                @RequestParam(name = "page", defaultValue = "1") int page ){
        Mono<SearchResultDTO> searchResult = kakaoBookApiService.searchBooks(searchWord, page);

        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

}
