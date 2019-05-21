package com.twelvebooks.twelvebook.controller.api;

import com.twelvebooks.twelvebook.domain.Diary;
import com.twelvebooks.twelvebook.dto.DiaryDto;
import com.twelvebooks.twelvebook.dto.PostReviewResultDto;
import com.twelvebooks.twelvebook.service.ChallengeService;
import com.twelvebooks.twelvebook.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/diaries")
public class DiaryAPIController {

    @Autowired
    DiaryService diaryService;
    @Autowired
    ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<PostReviewResultDto> postReview(@RequestBody DiaryDto diaryDto){
        PostReviewResultDto postReviewResultDto = new PostReviewResultDto();
        char[] c = diaryDto.getContent().toCharArray();
        int length = c.length;

        //TODO 나중에 DTO만들어서 관리하는걸로 변경하기
        if(length < 30){
            postReviewResultDto.setResult("일지는 30자 이상 작성해주세요.");
            return new ResponseEntity<>(postReviewResultDto, HttpStatus.OK);
        }

        int count = diaryService.addDiary(diaryDto);
        if(count > 0){
            postReviewResultDto.setResult("이미 작성된 일지 있음");
            return new ResponseEntity<>(postReviewResultDto, HttpStatus.CONFLICT);
        }
        postReviewResultDto.setResult("작성완료");
        return new ResponseEntity<>(postReviewResultDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{diaryId}")
    public ResponseEntity<PostReviewResultDto> deleteReview(@PathVariable(value = "diaryId") Long id, Principal principal){
        PostReviewResultDto postReviewResultDto = new PostReviewResultDto();

        Diary diary = diaryService.getDiaryById(id);

        diaryService.deleteDiary(diary);

        postReviewResultDto.setResult("삭제완료");
        return new ResponseEntity<>(postReviewResultDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostReviewResultDto> modifyReview(@RequestBody DiaryDto diaryDto){
        PostReviewResultDto postReviewResultDto = new PostReviewResultDto();

        System.out.println("api" + diaryDto.getId());
        int update = diaryService.updateDiary(diaryDto);

        postReviewResultDto.setResult("수정완료");
        return new ResponseEntity<>(postReviewResultDto, HttpStatus.OK);
    }

    @GetMapping
    public List<Diary> getDiariesByChallengeId(@RequestParam(name = "challengeId") long id){
        return null;
    }
}
