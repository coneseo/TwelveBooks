package com.twelvebooks.twelvebook.service;


import com.twelvebooks.twelvebook.domain.Bookmark;

import java.util.List;

public interface BookmarkService {


    public List<Bookmark> bookmarkList(long id);

    public Bookmark addBookmark(Bookmark bookmark);

    public void deleteBookmark(long id);

    public Bookmark selectDelete(Bookmark bookmark, long userid);

    public Bookmark getBookmark(String isbn);

    public Bookmark getBookmarkById(long id, long userid);

    public Bookmark getBookmarkbyIsbnUser(String isbn, long id);


}
