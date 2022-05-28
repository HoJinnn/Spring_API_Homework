package com.sparta.springapihomework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequestDto {
    private final String username;
    private final String password;
    private final String checkPassword;

}
