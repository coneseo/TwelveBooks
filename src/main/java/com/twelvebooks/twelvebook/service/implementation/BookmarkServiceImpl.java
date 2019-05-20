package com.twelvebooks.twelvebook.service.implementation;

import com.twelvebooks.twelvebook.domain.Bookmark;
import com.twelvebooks.twelvebook.repository.BookmarkRepository;
import com.twelvebooks.twelvebook.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookmarkServiceImpl implements BookmarkService {


    @Autowired
    BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> bookmarkList(long id) {

        List<Bookmark> bookmarks  = bookmarkRepository.getBookmarks(id);
        return bookmarks;
//        return bookmarks;
    }

    @Override
    @Transactional
    public Bookmark addBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }


    @Override
    @Transactional
    public void deleteBookmark(long id) {
        bookmarkRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Bookmark selectDelete(Bookmark bookmark, long userid) {
        return bookmarkRepository.selectDel(bookmark, userid);
    }

    @Override
    public Bookmark getBookmark(String isbn) {
        return bookmarkRepository.getBookmark(isbn);
    }


    @Override
    public Bookmark getBookmarkbyIsbnUser(String isbn, long id) {
        return bookmarkRepository.getBookmarkbyIsbnUser(isbn, id);
    }

    @Override
    public Bookmark getBookmarkById(long id, long userid) {
        return bookmarkRepository.getBookmarkById(id,userid);
    }



    //    @Override
//    @Transactional(readOnly = true)
//    public List<Bookmark> selectAllByUserId(Long userId) {
//        return bookmarkRepository.findAllByUserId(userId);
//    }
}
