package com.twelvebooks.twelvebook.controller;

import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.domain.Bookmark;
import com.twelvebooks.twelvebook.domain.User;
import com.twelvebooks.twelvebook.repository.BookmarkRepository;
import com.twelvebooks.twelvebook.repository.UserRepository;
import com.twelvebooks.twelvebook.service.BookService;
import com.twelvebooks.twelvebook.service.BookmarkService;
import com.twelvebooks.twelvebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    @Autowired
    BookmarkService bookmarkService;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/list")
    public String bookmarkList(Model model, Principal principal){

        User user = userService.getUserByEmail(principal.getName());
        List<Bookmark> bookmarks = bookmarkService.bookmarkList(user.getId());
        User username = userService.getUserById(user.getId());

        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("username", username);
        return "bookmark/list";
    }

    @GetMapping("/{id}")
    public String sendBookmark(@PathVariable(name="id")long id, Model model, Principal principal){

        User user = userService.getUserByEmail(principal.getName());


        Bookmark bookmark = bookmarkService.getBookmarkById(id, user.getId());

        if(bookmark !=null) {

            System.out.println("북마크의" + bookmark.getIsbn());

            Book book = new Book();
            Book checkbook = bookService.getBookdataByIsbn(bookmark.getIsbn());

            System.out.println("체크북의" + checkbook.getIsbn());

            if (checkbook != null) {


                book.setId(checkbook.getId());
                book.setIsbn(checkbook.getIsbn());
                book.setTitle(checkbook.getTitle());
                book.setAuthor(checkbook.getAuthor());
                book.setPublisher(checkbook.getPublisher());
                book.setTranslator(checkbook.getTranslator());
                book.setThumbnailImage(checkbook.getThumbnailImage());

                model.addAttribute("bookdata", book);

                Bookmark bookmarks = bookmarkService.getBookmarkbyIsbnUser(checkbook.getIsbn(), user.getId());
                bookmarkService.deleteBookmark(bookmarks.getId());

            }


        }
        else{

            return "index";
        }


    return "challenges/addform";
    }
}
