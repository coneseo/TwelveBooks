package com.twelvebooks.twelvebook.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SearchResultDTO {
    private List<SearchBookDTO> documents;
    private Map<String, String> meta;
}
