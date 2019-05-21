package com.twelvebooks.twelvebook.controller.api;

import com.twelvebooks.twelvebook.domain.User;
import com.twelvebooks.twelvebook.dto.EmailCheckDto;
import com.twelvebooks.twelvebook.dto.EmailDto;
import com.twelvebooks.twelvebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailCheckAPIController {

    @Autowired
    UserService userService;


    @GetMapping
    public Optional<User> getUserByEmail(@RequestParam(name = "email") String email){
        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(email));
        return user;
    }

    @PostMapping
    public ResponseEntity<EmailCheckDto> emailCheck(@RequestBody EmailDto emailDto){
        EmailCheckDto emailCheckDto = new EmailCheckDto();
        int emailCheck = userService.emailCheck(emailDto.getEmail());
        if(emailCheck > 0){
            emailCheckDto.setResult("중복된 이메일 있음");
            return new ResponseEntity<>(emailCheckDto, HttpStatus.CONFLICT);
        }
        emailCheckDto.setResult("중복된 이메일 없음");
        return new ResponseEntity<>(emailCheckDto, HttpStatus.OK);
    }
}
