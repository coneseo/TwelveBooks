package com.twelvebooks.twelvebook.service;

import com.twelvebooks.twelvebook.domain.Diary;
import com.twelvebooks.twelvebook.dto.DiaryDto;

import java.util.List;

public interface DiaryService {
    //다이어리 등록
    public int addDiary(DiaryDto diarydto);

    //챌린지에 따른 다이어리들 가져오기
    public List<Diary> getDiariesByChallengeId(long challengeIdd);

    public int diaryCheck(long id, int days);

    public void deleteDiary(Diary diary);

    public Diary getDiaryById(Long id);

    public int updateDiary(DiaryDto diaryDto);
}
