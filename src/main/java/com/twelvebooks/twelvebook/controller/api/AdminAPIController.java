package com.twelvebooks.twelvebook.controller.api;

import com.twelvebooks.twelvebook.domain.Role;
import com.twelvebooks.twelvebook.domain.User;
import com.twelvebooks.twelvebook.dto.UserAPIResultDto;
import com.twelvebooks.twelvebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequestMapping("/api/admins")
@RestController
@RequiredArgsConstructor
public class AdminAPIController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserAPIResultDto> getUserById(@RequestParam Long userId){
            return null;
    }

    @PostMapping
    public ResponseEntity<UserAPIResultDto> modifyUserRole(@RequestParam(name = "userEmail") String userEmail,
                                                           @RequestParam(name = "roleName") String roleName){
        UserAPIResultDto userAPIResultDto = new UserAPIResultDto();
        User user = userService.getUserByEmail(userEmail);
        Role role = userService.getUserRole(roleName);
        //사용자 메일이랑 권한 이름이 들어오면,
        //관리자 권한 추가하거나, 사용자 중지 시키려면 role을 삭제해버리자
        //이미 관리자 입니다. 관리자로 권한을 조정했습니다.
        //유저로 권한을 조정했습니다.
        //이미 정지된 사용자 입니다. 정지가 완료되었습니다.
        //정지가 해지 되었습니다.

        if(user.getRoles().isEmpty()){
            user.addUserRole(role);
        }
        if(user.getRoles().contains(role)){
            userAPIResultDto.setUserResultMessage("이미 있는 권한입니다.");

        }else{
//            userService.modifyUserRole(user, role);
            user.addUserRole(role);

            userAPIResultDto.setUserResultMessage("권한을 조정했습니다.");
        }


        return new ResponseEntity<>(userAPIResultDto, HttpStatus.OK);
    }
//    @DeleteMapping
//    public ResponseEntity<UserAPIResultDto>

}
