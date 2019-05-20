package com.twelvebooks.twelvebook.controller.api;


import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.dto.BookDto;
import com.twelvebooks.twelvebook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookAPIController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public String bookAdd(@RequestBody BookDto bookDto){

        String checkisbn = bookDto.getIsbn();

        if(checkisbn != null) {

            System.out.println(checkisbn);

            Book check = bookService.getBookByIsbn(checkisbn);

            System.out.println(check);

            if (check == null) {
                Book book = new Book();

                BeanUtils.copyProperties(bookDto, book);

                bookService.addBook(book);
            } else {

            }
            return "challenges/addform";

        }

        else {
            return "pass";
        }
    }
}
