package com.twelvebooks.twelvebook.service;

import com.twelvebooks.twelvebook.domain.Role;
import com.twelvebooks.twelvebook.domain.User;

import java.util.List;

public interface UserService {
    public User addUser(User user); //회원가입
    public User getUserByEmail(String email); //로그인
    public Role getUserRole(String roleName);
    public  int emailCheck(String email);
    public List<User> getUsers();
    public User getUserById(Long id);
    public String modifyUserRole(User user, Role role);
}
