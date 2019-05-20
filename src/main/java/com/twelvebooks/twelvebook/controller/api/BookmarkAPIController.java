package com.twelvebooks.twelvebook.controller.api;


import com.twelvebooks.twelvebook.controller.BookmarkController;
import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.domain.Bookmark;
import com.twelvebooks.twelvebook.domain.User;
import com.twelvebooks.twelvebook.dto.BookDto;
import com.twelvebooks.twelvebook.dto.BookmarkDto;
import com.twelvebooks.twelvebook.repository.BookmarkRepository;
import com.twelvebooks.twelvebook.repository.UserRepository;
import com.twelvebooks.twelvebook.service.BookService;
import com.twelvebooks.twelvebook.service.BookmarkService;
import com.twelvebooks.twelvebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkAPIController {

    @Autowired
    BookmarkService bookmarkService;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @DeleteMapping(value = "/{bookmarkId}")
    public int delete(@PathVariable(value = "bookmarkId") Long id, Principal principal){
        User user = userService.getUserByEmail(principal.getName());
        bookmarkService.deleteBookmark(id);

        return bookmarkService.bookmarkList(user.getId()).size();
    }





    @PostMapping
    public String addBookmark(@RequestBody BookmarkDto bookmarkDto, Principal principal) {

        User user = userService.getUserByEmail(principal.getName());


        String isbn = bookmarkDto.getIsbn();

        System.out.println("체크isbn" + isbn);

        Optional<Bookmark> check = Optional.ofNullable(bookmarkService.getBookmarkbyIsbnUser(isbn, user.getId()));

        System.out.println("체크체크" + check);

        if(!check.isPresent()){


            Bookmark bookmark = new Bookmark();

            System.out.println("북체크체크" + bookmark.getUser());

            bookmark.setUser(user);

            System.out.println("북체크체크" + bookmark.getUser());

            Book book = bookService.getBookdataByIsbn(isbn);

            bookmark.setBook(book);
            bookmark.setIsbn(book.getIsbn());
            bookmark.setThumbnailImage(book.getThumbnailImage());
            bookmark.setBookTitle(book.getTitle());

            System.out.println("북체크체크" + book.getId());

            bookmarkService.addBookmark(bookmark);
            return "save";


        }
        else {

            return "exist";
        }
    }

//    @GetMapping
//    public String sendBookmark(@PathVariable(name="id")long id, Model model, Principal principal){
//
//        User user = userService.getUserByEmail(principal.getName());
//
//
//        Bookmark bookmark = bookmarkService.getBookmarkById(id, user.getId());
//
//        if(bookmark !=null) {
//
//            System.out.println("북마크의" + bookmark.getIsbn());
//
//            Book book = new Book();
//            Book checkbook = bookService.getBookdataByIsbn(bookmark.getIsbn());
//
//            System.out.println("체크북의" + checkbook.getIsbn());
//
//            if (checkbook != null) {
//
//
//                book.setId(checkbook.getId());
//                book.setIsbn(checkbook.getIsbn());
//                book.setTitle(checkbook.getTitle());
//                book.setAuthor(checkbook.getAuthor());
//                book.setPublisher(checkbook.getPublisher());
//                book.setTranslator(checkbook.getTranslator());
//                book.setThumbnailImage(checkbook.getThumbnailImage());
//
//                model.addAttribute("bookdata", book);
//
//                Bookmark bookmarks = bookmarkService.getBookmarkbyIsbnUser(checkbook.getIsbn(), user.getId());
//                bookmarkService.deleteBookmark(bookmarks.getId());
//
//            }
//
//
//        }
//        else{
//
//            return "index";
//        }
//
//
//        return "challenges/addform";
//    }




}
