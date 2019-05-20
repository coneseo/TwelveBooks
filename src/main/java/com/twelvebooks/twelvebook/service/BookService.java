package com.twelvebooks.twelvebook.service;

import com.twelvebooks.twelvebook.domain.Book;

import java.util.List;

public interface BookService {

    //isbn에 해당하는 책 찾아 저장
    Book getBookByIsbn(String isbn);

    //책 등록하기
    Book addBook(Book book);

    // isbn에 해당하는 책 찾기
    Book getBookdataByIsbn(String isbn);


}
