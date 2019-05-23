package com.twelvebooks.twelvebook.service.implementation;

import com.twelvebooks.twelvebook.dto.SearchResultDTO;
import com.twelvebooks.twelvebook.util.RestProperties;
import com.twelvebooks.twelvebook.service.KakaoBookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoBookApiServiceImpl implements KakaoBookApiService {

    @Autowired
    private RestProperties restProperties;

//    @Autowired
//    WebClient.Builder webClientBuild;
    @Autowired
    WebClient webClient;

//    private static final String API_BOOK_URL = "https://dapi.kakao.com/v3/search/book";

//    @Override
//    public Map<String, Object> searchBooks(String searchWord, String target, String category, int page) {
//        final String URL = API_BOOK_URL + "?target=" + target +  "&category=" + category + "&page="
//                + page;
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "KakaoAK " + restProperties.getProperty("admin.booksearch"));
//        Map<String, String> params = new HashMap<>();
//        params.put("query", searchWord);
//        String jsonString = null;
//        Map<String, Object> resultData = null;
//        try {
//            jsonString = Utils.getHttpPOST2String(URL, headers, params);
//            ObjectMapper mapper = new ObjectMapper();
//            resultData = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return resultData;
//    }

    @Override
    public Mono<SearchResultDTO> searchBooks(String searchWord, int page) {
//        WebClient webClient = webClientBuild.baseUrl("https://dapi.kakao.com").build();
        Mono<SearchResultDTO> searchResult = webClient.get().uri("/v3/search/book?query=" + searchWord + "&page="+page)
                .header("Authorization", "KakaoAK " + restProperties.getProperty("admin.booksearch"))
                .retrieve()
                .bodyToMono(SearchResultDTO.class);
        return searchResult;
    }
}
