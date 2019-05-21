package com.twelvebooks.twelvebook.service.implementation;

import com.twelvebooks.twelvebook.domain.Challenge;
import com.twelvebooks.twelvebook.domain.Diary;
import com.twelvebooks.twelvebook.dto.DiaryDto;
import com.twelvebooks.twelvebook.repository.ChallengeRepository;
import com.twelvebooks.twelvebook.repository.DiaryRepository;
import com.twelvebooks.twelvebook.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public int addDiary(DiaryDto diarydto) {
        long chalId = diarydto.getChalid();

        Challenge challenge = challengeRepository.getChallengeDetail(chalId);

        Diary diary = new Diary();

        diary.setContent(diarydto.getContent());
        diary.setDays(diarydto.getDays());
        diary.setChallenge(challenge);

        int check = diaryRepository.diaryCheck( chalId, diarydto.getDays());
        if(check > 0){
            return check;
        }else if(check == 0){
            diaryRepository.save(diary);
        }

        return check;
    }

    @Override
    @Transactional
    public List<Diary> getDiariesByChallengeId(long ChallengeId) {
        List<Diary> list = null;
        list = diaryRepository.getDiariesByChallengeId(ChallengeId);
        return list;
    }

    @Override
    @Transactional
    public int diaryCheck(long id, int days) {
        int count = 0;
        count = diaryRepository.diaryCheck(id, days);
        return count;
    }

    @Override
    public void deleteDiary(Diary diary) {
        diaryRepository.delete(diary);
    }

    @Override
    public Diary getDiaryById(Long id) {
        Diary diary = null;
        diary = diaryRepository.getOne(id);
        return diary;
    }

    @Override
    @Transactional
    public int updateDiary(DiaryDto diaryDto) {
        System.out.println(diaryDto.getId());
        System.out.println(diaryDto.getContent());
        diaryRepository.updateDiary(diaryDto.getId(), diaryDto.getContent());

        return 0;
    }
}
