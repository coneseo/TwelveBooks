package com.twelvebooks.twelvebook.service;

import com.twelvebooks.twelvebook.dto.SearchResultDTO;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface KakaoBookApiService {
    //책검색
//    public Map<String, Object> searchBooks(String searchWord, String target, String category, int page);

    //
    public Mono<SearchResultDTO> searchBooks(String searchWord, int page);
}
