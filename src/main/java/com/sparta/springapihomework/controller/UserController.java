package com.sparta.springapihomework.controller;

import com.sparta.springapihomework.dto.SignupRequestDto;
import com.sparta.springapihomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    //회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //회원가입 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }
}
