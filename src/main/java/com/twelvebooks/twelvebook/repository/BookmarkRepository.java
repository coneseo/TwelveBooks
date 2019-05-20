package com.twelvebooks.twelvebook.repository;


import com.twelvebooks.twelvebook.domain.Book;
import com.twelvebooks.twelvebook.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    //user id 에 해당하는 bookmark list를 가져온다.
    @Query("SELECT b FROM Bookmark b WHERE b.user.id = :id ORDER BY b.id DESC")
    public List<Bookmark> getBookmarks(@Param("id") long id);

    //체크박스 삭제
    @Query("DELETE FROM Bookmark b WHERE b.id = :bookmark and b.user.id = :userid")
    public Bookmark selectDel(Bookmark bookmark, long userid);


    //bookmark안에 isbn에 해당하는 정보를 가져온다.
    @Query("SELECT b FROM Bookmark b WHERE b.isbn = :isbn ")
    public Bookmark getBookmark(String isbn);

    //bookmark안에 id에 해당하는 정보를 가져온다.
    @Query("SELECT b FROM Bookmark b WHERE b.id = :id and b.user.id = :userid")
    public Bookmark getBookmarkById(@Param("id") long id, long userid);


    //bookmark안에 isbn에 해당하는 정보를 가져온다.
    @Query("SELECT b FROM Bookmark b WHERE b.isbn =  ?1 and b.user.id=  ?2")
    public Bookmark getBookmarkbyIsbnUser(String isbn, long id);


}
