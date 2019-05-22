package com.twelvebooks.twelvebook.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchBookDTO {

    private String title;
    private List<String> authors;
    private String isbn;
    private List<String> translators;
    private String thumbnail;
    private String publisher;

}
