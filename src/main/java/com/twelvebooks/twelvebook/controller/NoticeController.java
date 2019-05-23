package com.twelvebooks.twelvebook.controller;


import com.twelvebooks.twelvebook.domain.Notice;
import com.twelvebooks.twelvebook.repository.NoticeRepository;
import com.twelvebooks.twelvebook.security.SecurityUser;
import com.twelvebooks.twelvebook.service.ImageFileService;
import com.twelvebooks.twelvebook.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {


    //todo  이미지 파일 넣기
//    @Autowired
//    private ImageFileService imageFileService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public String noticeList(Model model,@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 10 ) Pageable pageable){

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10,new Sort(Sort.Direction.DESC, "id"));


        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        model.addAttribute("noticepage", noticePage);
        return "notices/list";

    }

    @GetMapping("/{id}")
    public String noticeDetail(@PathVariable(name="id")Long id,
                               Model model){
        Notice notice = noticeService.noticeDetail(id);
        model.addAttribute("notice", notice);
        return "notices/detail";
    }


    @GetMapping("/write")
    public String writeForm(Model model){
        return "notices/write";
    }

    @PostMapping("/write")
    public String noticeWrite(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "content") String content
//        @RequestParam(name = "image") MultipartFile[] images
        ){


             Notice notice = new Notice();
             notice.setContent(content);
             notice.setTitle(title);

             // 이미지 추가

             noticeService.noticeWrite(notice);
        return "redirect:/notices/list";
    }

    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable(name="id")Long id,
                             Model model){

        Notice notice = noticeService.noticeDetail(id);
        model.addAttribute("notice", notice);
        return "notices/modify";
    }

    @PostMapping("/modify/{id}")
    public String noticeModify(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @PathVariable(name="id")Long id,
            Notice notice
//        @RequestParam(name = "image") MultipartFile[] images
    ){
        notice.setContent(content);
        notice.setTitle(title);

        // 이미지 추가

        noticeService.noticeModify(notice, id);
        return "redirect:/notices/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") Long id){
        noticeRepository.deleteById(id);
        return "redirect:/notices/list";
    }



}
