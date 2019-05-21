package com.twelvebooks.twelvebook.repository;

import com.twelvebooks.twelvebook.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    //1.Challenge에 해당하는 다이어리 가져오기
    @Query(value = "SELECT d FROM Diary d INNER JOIN FETCH d.challenge WHERE d.challenge.id = :id")
    public List<Diary> getDiariesByChallengeId(@Param("id") long id);

    //2.User에 해당하는 다이어리 가져오기
    @Query(value = "SELECT d FROM Diary d " +
                    "INNER JOIN FETCH d.challenge c " +
                    "INNER JOIN FETCH c.user u " +
                    "WHERE u.id = :id")
    public List<Diary> getDiariesByCIdAndUid(@Param("id") long id);

    @Query("SELECT count(d.content) FROM Diary d INNER JOIN d.challenge WHERE d.challenge.id = :id AND d.days = :days")
    public int diaryCheck(@Param("id") long id,
                          @Param("days") int days);

    @Modifying
    @Query("UPDATE Diary  d SET d.content = :content WHERE d.id =:id")
    public int updateDiary(@Param("id")long id,
                           @Param("content") String content);
}
