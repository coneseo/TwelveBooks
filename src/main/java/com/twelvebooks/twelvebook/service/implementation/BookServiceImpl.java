package com.twelvebooks.twelvebook.service.implementation;

import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.repository.BookRepository;
import com.twelvebooks.twelvebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;


    @Override
    public Book getBookByIsbn(String isbn) {
        Book book1 = null;
        Optional<Book> book = Optional.ofNullable(bookRepository.getBookByIsbn(isbn));
        if(book.isPresent()){
             book1 = book.get();
        }
        return book1;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public Book getBookdataByIsbn(String isbn) {
        Book book = bookRepository.getBookByIsbn(isbn);

        return book;
    }


}
