package com.twelvebooks.twelvebook.repository;

import com.twelvebooks.twelvebook.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    //List<Book>전부 가져오기
    @Query(value = "SELECT b FROM Book b ORDER BY b.id DESC",
            countQuery = "SELECT count(b) FROM Book b"
    )
    public List<Book> getBooks( int start, int limit, String searchKind, String searchStr);

    //isbn에 해당하는 Book가져오기
    @Query(value = "SELECT b FROM Book b WHERE b.isbn = :isbn")
    public Book getBookByIsbn(String isbn);

    //책 제목, 번역, 출판사등의 정보로 찾기
    @Query(value = "SELECT b FROM Book b WHERE b.title = :inputSearch or b.author =:inputSearch or b.publisher =:inputSearch or b.translator=:inputSearch")
    public Book getBookBySearch(String inputSearch);


}
