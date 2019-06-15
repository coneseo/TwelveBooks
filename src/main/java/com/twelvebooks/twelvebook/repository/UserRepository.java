package com.twelvebooks.twelvebook.repository;

import com.twelvebooks.twelvebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //email에 해당하는 User 가져오기
    @Query("SELECT u FROM User u WHERE u.email =:email")
    public User getUserByEmail(@Param("email") String email);

    //id에 해당하는 User 가져오기
    @Query("SELECT u FROM User u WHERE u.id =:id")
    public User getUserById(@Param("id") Long id);

    //User전체 리스트 가져오기
    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    public List<User> getUsers();

    @Query("SELECT count(u.email) FROM User u WHERE u.email = :email")
    public int emailCheck(@Param("email") String email);

    // 유저 닉네임, 이메일로 찾기
    @Query("SELECT count(u.email) FROM User u WHERE u.email = :inputSearch or u.name =:inputSearch")
    public User getUserBySearch(String inputSearch);

}
