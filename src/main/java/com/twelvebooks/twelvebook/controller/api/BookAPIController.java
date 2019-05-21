package com.twelvebooks.twelvebook.controller.api;


import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.dto.BookDto;
import com.twelvebooks.twelvebook.dto.BookResultDto;
import com.twelvebooks.twelvebook.dto.BookmarkResultDto;
import com.twelvebooks.twelvebook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookAPIController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookResultDto> addBook(@RequestBody BookDto bookDto){
        BookResultDto bookmarkResultDto = new BookResultDto();
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
            bookmarkResultDto.setResult("save");
            return new ResponseEntity<>(bookmarkResultDto, HttpStatus.OK);

        }

        else {
            bookmarkResultDto.setResult("conflict");
            return new ResponseEntity<>(bookmarkResultDto, HttpStatus.CONFLICT);
        }
    }
}
